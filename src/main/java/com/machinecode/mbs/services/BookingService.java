package com.machinecode.mbs.services;

import com.machinecode.mbs.dtos.BookingRequestDto;

import com.machinecode.mbs.exceptions.SeatNotAvailableException;
import com.machinecode.mbs.exceptions.ShowNotFoundException;
import com.machinecode.mbs.exceptions.UserNotFoundException;
import com.machinecode.mbs.models.*;
import com.machinecode.mbs.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookingService {
    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private ShowSeatTypeRepository showSeatTypeRepository;
    private BookingRepository bookingRepository;

    public BookingService(UserRepository userRepository, ShowRepository showRepository, ShowSeatRepository showSeatRepository, ShowSeatTypeRepository showSeatTypeRepository, BookingRepository bookingRepository, BookingRepository bookingRepository1) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.showSeatTypeRepository = showSeatTypeRepository;
        this.bookingRepository = bookingRepository;
    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking createBooking(Integer userId, List<Integer> showSeatIds, Integer showId) throws UserNotFoundException, ShowNotFoundException, SeatNotAvailableException {
        //first get all the data from the repositories needed to create the Booking object.
        /*
        booking number, user, list<showSeat>, list<payment>
        1. get the user with that id.
        2. get the list of seats and the show.
        -------------TAKE A LOCK----------------
        3.
         */
        //! USER
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User with id "+userId+" does not exist.");
        }
        User user = optionalUser.get();

        //! SHOW
        Optional<Show> optionalShow = showRepository.findById(showId);
        if(optionalShow.isEmpty()){
            throw new ShowNotFoundException("Show with id "+showId+" does not exist.");
        }
        Show show = optionalShow.get();

        //now we have the list of showSeat ids. From that we get the showSeat objects.
        //we have the list of ids, and we want the list of objects from its table

        //kind of like: select * from show_seats where id IN(1,2,3,4,5)
        //! SHOW_SEATS
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);

        /*
        * Take the lock and then check if the seats selected are available or not.
        * If not then throw an exception.
        * If yes, then mark the status as BLOCKED.
         */
        for(ShowSeat showSeat : showSeats){
            if(showSeat.getShowSeatStatus()!= ShowSeatStatus.AVAILABLE){
                throw new SeatNotAvailableException("Seat with id: "+showSeat.getId()+" is not available");
            }
        }
        //we are sure that the seats are available, and we need to block it.
        for(ShowSeat showSeat : showSeats){
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            showSeatRepository.save(showSeat);
            /*
            save works here based on the id. if there is an object with an id then it updates else it inserts.
             */
        }
        //the lock will be released and the DB will be updated.
        //then we move to create a booking object
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setCreatedAt(new Date());
        booking.setShowSeats(showSeats);
        int amount = 0;
        //with the show seat we need to get the price of this seat using the showSeatType.
//        for(ShowSeat showSeat : showSeats){
//            Show this_show = showSeat.getShow();
//            SeatType this_seat = showSeat.getSeat().getSeatType();
//            amount+=showSeatTypeRepository.findByShowAndSeatType(this_show, this_seat);
//        }
        //above is not optimal as we need to make db calls every time.
        //we have the showId here and the list of showSeat which will give us the type of the seat.
        //find all the showSeatType rows for this showId
        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShowId(show.getId());
        //then iterate through this list and add up the amount
        Map<SeatType, Integer> priceMap = new HashMap<>();
        priceMap = showSeatTypes.stream().collect(Collectors.toMap(a -> a.getSeatType(), a -> a.getPrice(),
                (existingValue, newValue) -> existingValue));
        for(ShowSeat showSeat : showSeats){
            amount+=priceMap.get(showSeat.getSeat().getSeatType());
        }
        booking.setAmount(amount);
        bookingRepository.save(booking);
        return booking;
    }
}

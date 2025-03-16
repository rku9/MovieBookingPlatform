package com.machinecode.mbs.controllers;

import com.machinecode.mbs.dtos.BookingRequestDto;
import com.machinecode.mbs.dtos.BookingResponseDto;
import com.machinecode.mbs.dtos.ResponseStatus;
import com.machinecode.mbs.exceptions.SeatNotAvailableException;
import com.machinecode.mbs.exceptions.ShowNotFoundException;
import com.machinecode.mbs.exceptions.UserNotFoundException;
import com.machinecode.mbs.models.Booking;
import com.machinecode.mbs.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {
    @Autowired
    private BookingService bookingService;

    public BookingResponseDto createBooking(BookingRequestDto bookingRequestDto){
        BookingResponseDto bookingResponseDto = new BookingResponseDto();
        //use the booking service to create an actual booking object and then convert it to dto.
        try{
            Booking booking = bookingService.createBooking(bookingRequestDto.getUserId(), bookingRequestDto.getShowSeatId(), bookingRequestDto.getShowId());
            bookingResponseDto.setBookingId(booking.getId()); //will be there after we have saved this in the db.
            bookingResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (UserNotFoundException | ShowNotFoundException | SeatNotAvailableException e) {
            bookingResponseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return bookingResponseDto;
    }
}

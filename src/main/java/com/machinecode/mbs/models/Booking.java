package com.machinecode.mbs.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.*;

@Getter
@Setter
@Entity
public class Booking extends BaseModel{
    private int bookingNumber;
    @ManyToOne
    private User user;
//    private Show show; //can be get through the ShowSeat
    @ManyToMany
    private List<ShowSeat> showSeats;
    private int amount;
    @OneToMany
    private List<Payment> payments;
    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;
}

/*
booking to user -> m:1
booking to show -> m:1. show can be a part of multiple bookings.
booking to showSeat -> m:1. a seat for a show can be cancelled and booked for later for other booking.
booking to payment -> 1:m
 */
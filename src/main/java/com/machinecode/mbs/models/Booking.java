package com.machinecode.mbs.models;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.*;

@Getter
@Setter
public class Booking extends BaseModel{
    int bookingNumber;
    User user;
    List<ShowSeat> seats;
}

package com.machinecode.mbs.models;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.*;

@Getter
@Setter
//for storing the price of the seat for a show.
public class ShowSeatType extends BaseModel{
    int showId;
    int seatId;
    int price;
}

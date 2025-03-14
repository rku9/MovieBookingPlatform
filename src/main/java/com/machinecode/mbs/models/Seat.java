package com.machinecode.mbs.models;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.*;

@Getter
@Setter
public class Seat extends BaseModel{
    private int number;
    private int row;
    private int column;
    private SeatType seatType;

}

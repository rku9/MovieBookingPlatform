package com.machinecode.mbs.models;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.*;

@Getter
@Setter
//this will store the status of the seat w.r.t. the shows
public class ShowSeat extends BaseModel{
    private Show show;
    private Seat seat;
    private ShowSeatStatus showSeatStatus;
}

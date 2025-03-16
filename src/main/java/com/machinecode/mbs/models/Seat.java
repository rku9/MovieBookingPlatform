package com.machinecode.mbs.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.*;

@Getter
@Setter
@Entity
public class Seat extends BaseModel{
    private int seatNumber;
    private int rowNum;
    private int columnNum;
    @Enumerated(EnumType.ORDINAL)
    private SeatType seatType;

}

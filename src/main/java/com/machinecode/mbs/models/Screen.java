package com.machinecode.mbs.models;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.*;

@Getter
@Setter
public class Screen extends BaseModel{
    private String name;
    //private Theatre theatre;
    private List<ScreenType> supportedTypes;
    private List<Seat> seats;
}

package com.machinecode.mbs.models;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.*;

@Getter
@Setter
public class Show extends BaseModel{
    private Date timing;
    private Movie movie;
    private Screen screen;
//    private Theatre theatre; // can be accessed from the Screen
}

package com.machinecode.mbs.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.*;

@Getter
@Setter
@Entity(name="shows")
public class Show extends BaseModel{
    private Date timing;
    @ManyToOne
    private Movie movie;
    @ManyToOne
    private Screen screen;
//    private Theatre theatre; // can be accessed from the Screen
}

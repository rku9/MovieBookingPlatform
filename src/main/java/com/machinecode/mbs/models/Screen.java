package com.machinecode.mbs.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.*;

@Getter
@Setter
@Entity
public class Screen extends BaseModel{
    private String name;
    //private Theatre theatre;
    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<ScreenType> supportedTypes;

    @OneToMany
    private List<Seat> seats;
}

/*
screen -> seat -> 1:m
 */
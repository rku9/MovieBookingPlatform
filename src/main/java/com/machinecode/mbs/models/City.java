package com.machinecode.mbs.models;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.*;

@Getter
@Setter
public class City extends BaseModel{
    private String name;
    private List<Theatre> theatres;
}

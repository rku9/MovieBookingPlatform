package com.machinecode.mbs.models;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.*;

@Getter
@Setter
//imax, 3d etc. has list of supported cities.
public class Platform extends BaseModel{
    private List<City> supportedCity;

}

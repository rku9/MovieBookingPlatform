package com.machinecode.mbs.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.*;

@Getter
@Setter
@Entity
//imax, 3d etc. has list of supported cities.
public class Platform extends BaseModel{
    @OneToMany
    private List<City> supportedCities;

}

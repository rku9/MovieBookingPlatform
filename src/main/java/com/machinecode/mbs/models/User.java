package com.machinecode.mbs.models;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.*;

@Getter
@Setter
public class User extends BaseModel {
    private String name;
    private String email;
    private List<Booking> bookings;
}

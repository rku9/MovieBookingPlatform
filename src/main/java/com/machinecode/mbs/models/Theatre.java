package com.machinecode.mbs.models;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.*;

@Getter
@Setter
public class Theatre extends BaseModel{
    private String name;
    private List<Screen> screens;
}

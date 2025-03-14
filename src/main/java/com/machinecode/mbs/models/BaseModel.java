package com.machinecode.mbs.models;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.*;

@Getter
@Setter
public class BaseModel {
    private int id;
    private Date createdAt;
    private Date lastModifiedAt;
}

package com.machinecode.mbs.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.*;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    @Id //would be used as the id of the tables that extend this class.
    @GeneratedValue(strategy = GenerationType.AUTO) //auto-increment
    private int id;
    private Date createdAt;
    private Date lastModifiedAt;
}

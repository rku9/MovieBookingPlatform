package com.machinecode.mbs.models;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.*;

@Getter
@Setter
@Entity
public class Movie extends BaseModel{
    private String title;
    private Date releaseDate;

    //collection of enums
    @Enumerated(EnumType.ORDINAL) //for enum
    @ElementCollection //for collection.
    private List<ScreenType> supportedScreenTypes;
}

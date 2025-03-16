package com.machinecode.mbs.models;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.*;

@Getter
@Setter
@Entity
public class Theatre extends BaseModel{
    private String name;
    @OneToMany
    private List<Screen> screens;
    //we could have also stored the city here but that relation is already defined
    //in the City class.
}

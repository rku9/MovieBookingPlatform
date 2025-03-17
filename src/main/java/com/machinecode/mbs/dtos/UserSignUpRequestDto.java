package com.machinecode.mbs.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//this will be passed to the service.
public class UserSignUpRequestDto {
    private String name;
    private String email;
    private String password;
}

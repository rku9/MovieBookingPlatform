package com.machinecode.mbs.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

@Getter
@Setter
public class LoginRequestDto {
    private String email;
    private String password;
}

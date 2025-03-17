package com.machinecode.mbs.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

@Configuration
public class BCryptEncoder {
    @Bean
    public BCryptEncoder getBCryptEncoder(){
        return new BCryptEncoder();
    }
}

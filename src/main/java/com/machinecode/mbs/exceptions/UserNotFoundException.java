package com.machinecode.mbs.exceptions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class UserNotFoundException extends Exception {
    //parametrized constructor to accept the message.
    public UserNotFoundException(String message) {
        super(message);
    }
}

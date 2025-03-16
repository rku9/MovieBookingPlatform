package com.machinecode.mbs.dtos;

import com.machinecode.mbs.models.BookingStatus;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

@Getter
@Setter
public class BookingResponseDto {
    private int bookingId;
    private ResponseStatus responseStatus;
}

package com.machinecode.mbs.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookingRequestDto {
    private int userId;
    private int showId;
    private List<Integer> showSeatId;
}

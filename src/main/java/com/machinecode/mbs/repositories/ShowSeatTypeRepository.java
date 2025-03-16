package com.machinecode.mbs.repositories;

import com.machinecode.mbs.models.SeatType;
import com.machinecode.mbs.models.Show;
import com.machinecode.mbs.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Integer> {
    int findByShow(Show show);

    int findByShowAndSeatType(Show show, SeatType seatType);

    List<ShowSeatType> findAllByShowId(int showId);
}

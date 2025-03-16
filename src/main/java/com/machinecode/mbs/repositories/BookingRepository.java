package com.machinecode.mbs.repositories;

import com.machinecode.mbs.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}

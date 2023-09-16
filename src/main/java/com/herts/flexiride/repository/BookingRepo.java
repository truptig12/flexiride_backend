package com.herts.flexiride.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.herts.flexiride.entity.Booking;

public interface BookingRepo extends JpaRepository<Booking, Integer> {

	public List<Booking> findByUserId(int lUserId);

	public List<Booking> findByBookedUserId(int lBookedUserId);

	public List<Booking> findByCarId(int lCarId);

	public List<Booking> findByCarIdAndBookingStatus(int lCarId, String lBookingStatus);

}

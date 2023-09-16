package com.herts.flexiride.service;

import java.util.List;

import com.herts.flexiride.entity.Booking;

public interface BookingService {

	public Booking addBooking(Booking lBooking) throws Exception;

	public Booking updateBooking(Booking lBooking) throws Exception;

	public List<Booking> findByUserId(int lUserId);

	public List<Booking> findByBookedUserId(int lBookedUserId);

	public List<Booking> findByCarId(int lCarId);

	public Booking getByBookedId(int lBookedId);

	public List<Booking> getAllAcceptedBookingOnCarId(int lCarId);
}

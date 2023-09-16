package com.herts.flexiride.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.herts.flexiride.entity.Booking;
import com.herts.flexiride.repository.BookingRepo;
import com.herts.flexiride.service.BookingService;

@Service
public class BookingImpl implements BookingService {

	@Autowired
	BookingRepo lBookingRepo;

	@Override
	public Booking addBooking(Booking lBooking) throws Exception {
		return this.lBookingRepo.save(lBooking);
	}

	@Override
	public Booking updateBooking(Booking lBooking) throws Exception {
		Booking lBookingToUpdate = null;
		try {
			lBookingToUpdate = this.lBookingRepo.getById(lBooking.getBookingId());
			System.out.println("Booking To Update : " + lBookingToUpdate.toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Booking data not exist");
		}
		return this.lBookingRepo.save(lBookingToUpdate);
	}

	@Override
	public List<Booking> findByUserId(int lUserId) {
		List<Booking> lBooking = null;
		try {
			lBooking = lBookingRepo.findByUserId(lUserId);
		} catch (Exception e) {
			return new ArrayList<Booking>();
		}
		return lBooking;
	}

	@Override
	public List<Booking> findByBookedUserId(int lBookedUserId) {
		List<Booking> lBooking = null;
		try {
			lBooking = lBookingRepo.findByBookedUserId(lBookedUserId);
		} catch (Exception e) {
			return new ArrayList<Booking>();
		}
		return lBooking;
	}

	@Override
	public Booking getByBookedId(int lBookedId) {
		return this.lBookingRepo.getById(lBookedId);
	}

	@Override
	public List<Booking> findByCarId(int lCarId) {
		List<Booking> lBooking = null;
		try {
			lBooking = lBookingRepo.findByCarId(lCarId);
		} catch (Exception e) {
			return new ArrayList<Booking>();
		}
		return lBooking;
	}

	@Override
	public List<Booking> getAllAcceptedBookingOnCarId(int lCarId) {
		return lBookingRepo.findByCarIdAndBookingStatus(lCarId, "ACCEPTED");
	}
}

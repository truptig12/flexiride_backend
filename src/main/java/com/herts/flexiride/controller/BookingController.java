package com.herts.flexiride.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.herts.flexiride.dto.BookingDTO;
import com.herts.flexiride.entity.Booking;
import com.herts.flexiride.entity.Car;
import com.herts.flexiride.entity.ResponseEntity;
import com.herts.flexiride.entity.User;
import com.herts.flexiride.service.AvailabilityService;
import com.herts.flexiride.service.BookingService;
import com.herts.flexiride.service.CarService;
import com.herts.flexiride.service.UserService;
import com.herts.flexiride.utility.CastToDTOUtility;
import com.herts.flexiride.utility.DateUtility;

@RestController
public class BookingController {

	@Autowired
	BookingService lBookingService;

	@Autowired
	CarService lCarService;

	@Autowired
	UserService lUserService;

	@Autowired
	AvailabilityService lAvailabilityService;

	@RequestMapping(value = "/addBooking", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity addBooking(@RequestBody Booking lBooking) {
		Booking lBookingEntity;
		ResponseEntity lResponseEntity = new ResponseEntity();
		try {
			lBooking.setBookingStatus("ADDED");
			lBookingEntity = lBookingService.addBooking(lBooking);
			System.out.println(lBookingEntity);
			lResponseEntity.setResponseCode(200);
			lResponseEntity.setResponseDescription("Booking's details added successfully");
			lResponseEntity.setId(lBookingEntity.getBookingId());
		} catch (Exception e) {
			e.printStackTrace();
			lResponseEntity.setResponseCode(401);
			lResponseEntity.setResponseDescription(e.getMessage());
		}
		return lResponseEntity;
	}

	@RequestMapping(value = "/updateBooking", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity updateBooking(@RequestBody Booking lBooking) {
		Booking lBookingEntity;
		ResponseEntity lResponseEntity = new ResponseEntity();
		try {
			System.out.println(lBooking.toString());
			if (lBooking.getBookingId() == 0) {
				throw new Exception("Invalid BookingId");
			}
			lBookingEntity = lBookingService.updateBooking(lBooking);
			System.out.println(lBookingEntity.toString());
			lResponseEntity.setResponseCode(200);
			lResponseEntity.setResponseDescription("Booking's details updated successfully");
			lResponseEntity.setId(lBookingEntity.getBookingId());
		} catch (Exception e) {
			lResponseEntity.setResponseCode(401);
			lResponseEntity.setResponseDescription(e.getMessage());
		}
		return lResponseEntity;
	}

	@RequestMapping(value = "/acceptBooking", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity acceptBooking(@RequestParam(value = "id", required = false) int id) {
		Booking lBookingEntity;
		ResponseEntity lResponseEntity = new ResponseEntity();
		try {
			System.out.println("Booking Id : " + id);
			lBookingEntity = lBookingService.getByBookedId(id);
			if (lBookingEntity == null) {
				lResponseEntity.setResponseCode(402);
				lResponseEntity.setResponseDescription("Data not found");
				return lResponseEntity;
			} else {
				if (lBookingEntity.getBookingStatus().equals("ACCEPTED")) {
					lResponseEntity.setResponseCode(403);
					lResponseEntity.setResponseDescription("You have already accepted this booking");
					return lResponseEntity;
				}
				List<Booking> lBookings = lBookingService.getAllAcceptedBookingOnCarId(lBookingEntity.getCarId());
				for (Booking booking : lBookings) {
					if (DateUtility.compareDateInFromAndToDate(booking.getFromDate(), booking.getToDate(),
							lBookingEntity.getFromDate())
							|| DateUtility.compareDateInFromAndToDate(booking.getFromDate(), booking.getToDate(),
									lBookingEntity.getToDate())) {
						lResponseEntity.setResponseCode(403);
						lResponseEntity.setResponseDescription(
								"You have already accepted the booking on the given day. Booking Id : "
										+ booking.getBookingId());
						return lResponseEntity;
					}
				}
				lBookingEntity.setBookingStatus("ACCEPTED");
				lBookingService.addBooking(lBookingEntity);
			}
			lResponseEntity.setResponseCode(200);
			lResponseEntity.setResponseDescription("Booking accepted successfully");
			lResponseEntity.setId(lBookingEntity.getBookingId());
		} catch (Exception e) {
			lResponseEntity.setResponseCode(401);
			lResponseEntity.setResponseDescription(e.getMessage());
		}
		return lResponseEntity;
	}

	@RequestMapping(value = "/rejectBooking", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity rejectBooking(@RequestParam(value = "id", required = false) int id) {
		Booking lBookingEntity;
		ResponseEntity lResponseEntity = new ResponseEntity();
		try {
			System.out.println("Booking Id : " + id);
			lBookingEntity = lBookingService.getByBookedId(id);
			if (lBookingEntity == null) {
				lResponseEntity.setResponseCode(402);
				lResponseEntity.setResponseDescription("Data not found");
				return lResponseEntity;
			} else {
				lBookingEntity.setBookingStatus("REJECTED");
				lBookingService.addBooking(lBookingEntity);
			}
			lResponseEntity.setResponseCode(200);
			lResponseEntity.setResponseDescription("Booking rejected successfully");
			lResponseEntity.setId(lBookingEntity.getBookingId());
		} catch (Exception e) {
			lResponseEntity.setResponseCode(401);
			lResponseEntity.setResponseDescription(e.getMessage());
		}
		return lResponseEntity;
	}

	@RequestMapping(value = "/completBooking", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity completBooking(@RequestParam(value = "id", required = false) int id) {
		Booking lBookingEntity;
		ResponseEntity lResponseEntity = new ResponseEntity();
		try {
			System.out.println("Booking Id : " + id);
			lBookingEntity = lBookingService.getByBookedId(id);
			if (lBookingEntity == null) {
				lResponseEntity.setResponseCode(402);
				lResponseEntity.setResponseDescription("Data not found");
				return lResponseEntity;
			} else {
				lBookingEntity.setBookingStatus("COMPLETED");
				lBookingService.addBooking(lBookingEntity);
			}
			lResponseEntity.setResponseCode(200);
			lResponseEntity.setResponseDescription("Booking completed successfully");
			lResponseEntity.setId(lBookingEntity.getBookingId());
		} catch (Exception e) {
			lResponseEntity.setResponseCode(401);
			lResponseEntity.setResponseDescription(e.getMessage());
		}
		return lResponseEntity;
	}

	@RequestMapping(value = "/findByUserId", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity findByUserId(@RequestParam(value = "id", required = false) int lUserId) {
		System.out.println("User Id : " + lUserId);
		ResponseEntity lResponseEntity = new ResponseEntity();
		List<BookingDTO> lBookingDTOList = new ArrayList<BookingDTO>();
		List<Booking> lBooking = null;
		try {
			lBooking = lBookingService.findByUserId(lUserId);
			for (Booking booking : lBooking) {
				BookingDTO lBookingDTO = CastToDTOUtility.castBookingIntoDTO(booking);
				Car lCar = lCarService.findByCarId(lBookingDTO.getCarId());
				if (lCar == null) {
					lBookingDTO.setCar(null);
				} else {
					lBookingDTO.setCar(CastToDTOUtility.castCarIntoDTO(lCar));
				}
				User lUser = lUserService.findByUserId(lBookingDTO.getBookedUserId());
				if (lUser == null) {
					lBookingDTO.setUser(null);
				} else {
					lBookingDTO.setUser(CastToDTOUtility.castUserIntoDTO(lUser));
				}
				lBookingDTOList.add(lBookingDTO);
			}

			if (lBooking.size() == 0) {
				lResponseEntity.setResponseCode(402);
				lResponseEntity.setResponseDescription("Data not found");
				return lResponseEntity;
			}
			lResponseEntity.setResponseCode(200);
			lResponseEntity.setResponseDescription("success");
			lResponseEntity.setBookingList(lBookingDTOList);

		} catch (Exception e) {
			e.printStackTrace();
			lResponseEntity.setResponseCode(401);
			lResponseEntity.setResponseDescription(e.getMessage());
		}
		return lResponseEntity;
	}

	@RequestMapping(value = "/findByBookedUserId", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity findByBookedUserId(
			@RequestParam(value = "id", required = false) int lBookedUserId) {
		System.out.println("Booked User Id : " + lBookedUserId);
		ResponseEntity lResponseEntity = new ResponseEntity();
		List<BookingDTO> lBookingDTOList = new ArrayList<BookingDTO>();
		List<Booking> lBooking = null;
		try {
			lBooking = lBookingService.findByBookedUserId(lBookedUserId);
			for (Booking booking : lBooking) {
				BookingDTO lBookingDTO = CastToDTOUtility.castBookingIntoDTO(booking);
				Car lCar = lCarService.findByCarId(lBookingDTO.getCarId());
				if (lCar == null) {
					lBookingDTO.setCar(null);
				} else {
					lBookingDTO.setCar(CastToDTOUtility.castCarIntoDTO(lCar));
				}
				User lUser = lUserService.findByUserId(lBookingDTO.getUserId());
				if (lUser == null) {
					lBookingDTO.setUser(null);
				} else {
					lBookingDTO.setUser(CastToDTOUtility.castUserIntoDTO(lUser));
				}
				lBookingDTOList.add(lBookingDTO);
			}

			if (lBooking.size() == 0) {
				lResponseEntity.setResponseCode(402);
				lResponseEntity.setResponseDescription("Data not found");
				return lResponseEntity;
			}
			lResponseEntity.setResponseCode(200);
			lResponseEntity.setResponseDescription("success");
			lResponseEntity.setBookingList(lBookingDTOList);

		} catch (Exception e) {
			e.printStackTrace();
			lResponseEntity.setResponseCode(401);
			lResponseEntity.setResponseDescription(e.getMessage());
		}
		return lResponseEntity;
	}

}

package com.herts.flexiride.utility;

import java.util.Base64;

import com.herts.flexiride.dto.BookingDTO;
import com.herts.flexiride.dto.CarDetailsDTO;
import com.herts.flexiride.dto.UserDTO;
import com.herts.flexiride.entity.Booking;
import com.herts.flexiride.entity.Car;
import com.herts.flexiride.entity.User;

public class CastToDTOUtility {

	public static CarDetailsDTO castCarIntoDTO(Car lCar) {
		CarDetailsDTO lCarDetailsDTO = new CarDetailsDTO();
		lCarDetailsDTO.setCarId(lCar.getCarId());
		lCarDetailsDTO.setUserId(lCar.getUserId());
		lCarDetailsDTO.setBrand(lCar.getBrand());
		lCarDetailsDTO.setModel(lCar.getModel());
		lCarDetailsDTO.setColor(lCar.getColor());
		lCarDetailsDTO.setManOfYear(lCar.getManOfYear());
		lCarDetailsDTO.setNoOfSeats(lCar.getNoOfSeats());
		lCarDetailsDTO.setCountry(lCar.getCountry());
		lCarDetailsDTO.setCity(lCar.getCity());
		lCarDetailsDTO.setLicencePlate(lCar.getLicencePlate());
		lCarDetailsDTO.setFuelType(lCar.getFuelType());
		lCarDetailsDTO.setGearbox(lCar.getGearbox());
		lCarDetailsDTO.setEnging(lCar.getEngine());
		if (lCar.getImage1() != null)
			lCarDetailsDTO.setImage1(Base64.getEncoder().encodeToString(lCar.getImage1()));
		if (lCar.getImage2() != null)
			lCarDetailsDTO.setImage2(Base64.getEncoder().encodeToString(lCar.getImage2()));
		if (lCar.getImage3() != null)
			lCarDetailsDTO.setImage3(Base64.getEncoder().encodeToString(lCar.getImage3()));
		if (lCar.getImage4() != null)
			lCarDetailsDTO.setImage4(Base64.getEncoder().encodeToString(lCar.getImage4()));
		return lCarDetailsDTO;
	}

	public static UserDTO castUserIntoDTO(User lUser) {
		UserDTO lUserDTO = new UserDTO();
		lUserDTO.setUser_id(lUser.getId());
		lUserDTO.setEmail(lUser.getEmail());
		lUserDTO.setFirst_name(lUser.getFirstName());
		if (lUser.getLastName() != null)
			lUserDTO.setLast_name(lUser.getLastName());
		return lUserDTO;
	}

	public static BookingDTO castBookingIntoDTO(Booking lBooking) {
		BookingDTO lBookingDTO = new BookingDTO();
		lBookingDTO.setBookedUserId(lBooking.getBookedUserId());
		lBookingDTO.setBookingAmount(lBooking.getBookingAmount());
		lBookingDTO.setBookingId(lBooking.getBookingId());
		lBookingDTO.setCarId(lBooking.getCarId());
		lBookingDTO.setFromDate(lBooking.getFromDate());
		lBookingDTO.setToDate(lBooking.getToDate());
		lBookingDTO.setUserId(lBooking.getUserId());
		return lBookingDTO;
	}
}

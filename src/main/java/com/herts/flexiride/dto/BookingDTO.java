package com.herts.flexiride.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookingDTO {

	private int bookingId;

	private int userId;

	private int bookedUserId;

	private int carId;

	private Date fromDate;

	private Date toDate;

	private int bookingAmount;

	private int booked;

	private CarDetailsDTO car;

	private UserDTO user;
}

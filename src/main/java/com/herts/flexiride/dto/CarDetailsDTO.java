package com.herts.flexiride.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CarDetailsDTO {

	private int carId;

	private int userId;

	private String brand;

	private String model;

	private String color;

	private int manOfYear;

	private int noOfSeats;

	private String country;

	private String city;

	private String licencePlate;

	private String fuelType;

	private String gearbox;

	private String enging;

	private String image1;

	private String image2;

	private String image3;

	private String image4;

	private int fareAmount;

	private String fromDate;

	private String toDate;

	private UserDTO user;

}

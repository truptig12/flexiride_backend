package com.herts.flexiride.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "car")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Car extends ResponseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "carId", nullable = false)
	private int carId;

	@Column(name = "userId", nullable = false)
	private int userId;

	@Column(name = "brand", nullable = true, length = 45)
	private String brand;

	@Column(name = "model", nullable = true, length = 45)
	private String model;

	@Column(name = "color", nullable = true, length = 45)
	private String color;

	@Column(name = "manOfYear", nullable = true, length = 4)
	private int manOfYear;

	@Column(name = "noOfSeats", nullable = true, length = 1)
	private int noOfSeats;

	@Column(name = "country", nullable = true, length = 45)
	private String country;

	@Column(name = "city", nullable = true, length = 45)
	private String city;

	@Column(name = "licencePlate", nullable = true, length = 45)
	private String licencePlate;

	@Column(name = "fuelType", nullable = true, length = 45)
	private String fuelType;

	@Column(name = "gearbox", nullable = true, length = 45)
	private String gearbox;

	@Column(name = "engine", nullable = true, length = 45)
	private String engine;

	@Lob
	@Column(name = "image1", nullable = true, length = 20971520)
	private byte[] image1;

	@Lob
	@Column(name = "image2", nullable = true, length = 20971520)
	private byte[] image2;

	@Lob
	@Column(name = "image3", nullable = true, length = 20971520)
	private byte[] image3;

	@Lob
	@Column(name = "image4", nullable = true, length = 20971520)
	private byte[] image4;
	
	@Column(name = "brandVerified", length = 4)
	private int brandVerified = 0;

	@Column(name = "modelVerified", length = 4)
	private int modelVerified = 0;

	@Column(name = "manOfYearVerified", length = 4)
	private int manOfYearVerified = 0;

	@Column(name = "licencePlateVerified", length = 4)
	private int licencePlateVerified = 0;

}

package com.herts.flexiride.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "booking")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Booking extends ResponseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bookingId", nullable = false)
	private int bookingId;

	@Column(name = "userId", nullable = false)
	private int userId;

	@Column(name = "bookedUserId", nullable = false)
	private int bookedUserId;

	@Column(name = "carId", nullable = false)
	private int carId;

	@Column(name = "fromDate", nullable = false, length = 45)
	private Date fromDate;

	@Column(name = "toDate", nullable = false, length = 45)
	private Date toDate;

	@Column(name = "bookingAmount", nullable = false, length = 45)
	private int bookingAmount;

	@Column(name = "bookingStatus", nullable = false, length = 20)
	private String bookingStatus;
}

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
@Table(name = "availability")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Availability extends ResponseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "availabilityId", nullable = false)
	private int availabilityId;

	@Column(name = "userId", nullable = false)
	private int userId;

	@Column(name = "carId", nullable = false)
	private int carId;

	@Column(name = "fromDate", nullable = false, length = 45)
	private Date fromDate;

	@Column(name = "toDate", nullable = false, length = 45)
	private Date toDate;

	@Column(name = "city", nullable = false, length = 45)
	private String city;

	@Column(name = "fareAmount", nullable = false, length = 45)
	private int fareAmount;

}

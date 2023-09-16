package com.herts.flexiride.entity;

import java.util.List;

import com.herts.flexiride.dto.BookingDTO;
import com.herts.flexiride.dto.CarDetailsDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResponseEntity {
	private int responseCode;
	private String responseDescription;
	private int id;
	private List<CarDetailsDTO> carList;
	private List<BookingDTO> bookingList;
}

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

import com.herts.flexiride.dto.CarDetailsDTO;
import com.herts.flexiride.entity.Availability;
import com.herts.flexiride.entity.Car;
import com.herts.flexiride.entity.ResponseEntity;
import com.herts.flexiride.entity.User;
import com.herts.flexiride.service.AvailabilityService;
import com.herts.flexiride.service.CarService;
import com.herts.flexiride.service.UserService;
import com.herts.flexiride.utility.CastToDTOUtility;

@RestController
public class AvailabilityController {

	@Autowired
	AvailabilityService lAvailabilityService;

	@Autowired
	CarService lCarService;

	@Autowired
	UserService lUserService;

	@RequestMapping(value = "/addAvailability", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity addAvailability(@RequestBody Availability lAvailability) {
		Availability lAvailabilityEntity;
		ResponseEntity lResponseEntity = new ResponseEntity();
		try {
			lAvailability.setCity(lAvailability.getCity().toUpperCase());
			lAvailabilityEntity = lAvailabilityService.addAvailability(lAvailability);
			System.out.println(lAvailabilityEntity);
			lResponseEntity.setResponseCode(200);
			lResponseEntity.setResponseDescription("Availability's details added successfully");
			lResponseEntity.setId(lAvailabilityEntity.getAvailabilityId());
		} catch (Exception e) {
			e.printStackTrace();
			lResponseEntity.setResponseCode(401);
			lResponseEntity.setResponseDescription(e.getMessage());
		}
		return lResponseEntity;
	}

	@RequestMapping(value = "/updateAvailability", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity updateAvailability(@RequestBody Availability lAvailability) {
		Availability lAvailabilityEntity;
		ResponseEntity lResponseEntity = new ResponseEntity();
		try {
			System.out.println(lAvailability.toString());
			if (lAvailability.getAvailabilityId() == 0) {
				throw new Exception("Invalid AvailabilityId");
			}
			lAvailabilityEntity = lAvailabilityService.updateAvailability(lAvailability);
			System.out.println(lAvailabilityEntity.toString());
			lResponseEntity.setResponseCode(200);
			lResponseEntity.setResponseDescription("Availability's details updated successfully");
			lResponseEntity.setId(lAvailabilityEntity.getAvailabilityId());
		} catch (Exception e) {
			lResponseEntity.setResponseCode(401);
			lResponseEntity.setResponseDescription(e.getMessage());
		}
		return lResponseEntity;
	}

	@RequestMapping(value = "/getAvailabilityByCarId", method = RequestMethod.POST)
	public @ResponseBody Availability getAvailabilityByCarId(@RequestParam(value = "id", required = false) int lCarId) {
		System.out.println("Car Id : " + lCarId);
		Availability lAvailability = null;
		try {
			lAvailability = lAvailabilityService.findByCarId(lCarId);
			if (lAvailability == null) {
				lAvailability = new Availability();
				lAvailability.setResponseCode(401);
				lAvailability.setResponseDescription("Availability data not exist");
			} else {
				lAvailability.setResponseCode(200);
				lAvailability.setResponseDescription("success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			lAvailability = new Availability();
			lAvailability.setResponseCode(401);
			lAvailability.setResponseDescription(e.getMessage());
		}
		return lAvailability;
	}

	@RequestMapping(value = "/getAllAvailableCarOnDate", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity getAllAvailableCarOnDate(
			@RequestParam(value = "fromDate", required = true) String lFromDate,
			@RequestParam(value = "toDate", required = true) String ltoDate,
			@RequestParam(value = "city", required = true) String city) {
		System.out.println("From Date : " + lFromDate + " | To Date : " + ltoDate);
		ResponseEntity lResponseEntity = new ResponseEntity();
		List<CarDetailsDTO> lCarDetailsDTOList = new ArrayList<CarDetailsDTO>();
		try {
			List<Availability> lAvailabilityList = lAvailabilityService.getAllAvailableCarOnDate(lFromDate, ltoDate,
					city.toUpperCase());
			System.out.println("list: " + lAvailabilityList);
			for (Availability availability : lAvailabilityList) {
				CarDetailsDTO lCarDetailsDTO = new CarDetailsDTO();
				Car lCar = lCarService.findByCarId(availability.getCarId());

				if (lCar != null) {
					lCarDetailsDTO = CastToDTOUtility.castCarIntoDTO(lCarService.findByCarId(availability.getCarId()));
				}
				lCarDetailsDTO.setFareAmount(availability.getFareAmount());
				lCarDetailsDTO.setFromDate(lFromDate);
				lCarDetailsDTO.setToDate(ltoDate);
				User lUser = lUserService.findByUserId(lCarDetailsDTO.getUserId());
				if (lUser != null) {
					lCarDetailsDTO.setUser(CastToDTOUtility.castUserIntoDTO(lUser));
				}
				lCarDetailsDTOList.add(lCarDetailsDTO);
			}

			if (lAvailabilityList.size() == 0) {
				lResponseEntity.setResponseCode(402);
				lResponseEntity.setResponseDescription("Data not found");
				return lResponseEntity;
			}
			lResponseEntity.setResponseCode(200);
			lResponseEntity.setResponseDescription("success");
			lResponseEntity.setCarList(lCarDetailsDTOList);

		} catch (Exception e) {
			lResponseEntity.setResponseCode(401);
			lResponseEntity.setResponseDescription(e.getMessage());
		}
		return lResponseEntity;
	}

}

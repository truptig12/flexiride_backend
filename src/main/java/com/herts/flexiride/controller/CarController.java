package com.herts.flexiride.controller;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.herts.flexiride.dto.CarDetailsDTO;
import com.herts.flexiride.entity.Availability;
import com.herts.flexiride.entity.Car;
import com.herts.flexiride.entity.ResponseEntity;
import com.herts.flexiride.service.AvailabilityService;
import com.herts.flexiride.service.CarService;
import com.herts.flexiride.utility.CastToDTOUtility;

@RestController
public class CarController {

	@Autowired
	CarService lCarService;

	@Autowired
	AvailabilityService lAvailabilityService;

	@RequestMapping(value = "/addCar", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity addCar(@RequestBody Car lCar) {
		Car lCarEntity;
		ResponseEntity lResponseEntity = new ResponseEntity();
		try {
			lCarEntity = lCarService.addCar(lCar);
			System.out.println(lCarEntity);
			lResponseEntity.setResponseCode(200);
			lResponseEntity.setResponseDescription("Car's details added successfully");
			lResponseEntity.setId(lCarEntity.getCarId());
		} catch (Exception e) {
			e.printStackTrace();
			lResponseEntity.setResponseCode(401);
			lResponseEntity.setResponseDescription(e.getMessage());
		}
		return lResponseEntity;
	}

	@RequestMapping(value = "/updateCar", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity updateCar(@RequestBody Car lCar) {
		Car lCarEntity;
		ResponseEntity lResponseEntity = new ResponseEntity();
		try {
			if (lCar.getCarId() == 0) {
				throw new Exception("Invalid CarId");
			}
			lCarEntity = lCarService.updateCar(lCar);
			System.out.println(lCarEntity);
			lResponseEntity.setResponseCode(200);
			lResponseEntity.setResponseDescription("Car's details updated successfully");
			lResponseEntity.setId(lCarEntity.getCarId());
		} catch (Exception e) {
			e.printStackTrace();
			lResponseEntity.setResponseCode(401);
			lResponseEntity.setResponseDescription(e.getMessage());
		}
		return lResponseEntity;
	}

	@RequestMapping(value = "/updateCarImage", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity updateCarImage(@RequestParam(value = "id", required = false) int carId,
			@RequestParam(value = "image1", required = false) MultipartFile image1,
			@RequestParam(value = "image2", required = false) MultipartFile image2,
			@RequestParam(value = "image3", required = false) MultipartFile image3,
			@RequestParam(value = "image4", required = false) MultipartFile image4) {

		ResponseEntity lResponseEntity = new ResponseEntity();
		try {
			Car lCarEntity = new Car();
			lCarEntity.setCarId(carId);

			if (image1 != null && image1.getSize() > 0)
				lCarEntity.setImage1(image1.getBytes());
			if (image2 != null && image2.getSize() > 0)
				lCarEntity.setImage2(image2.getBytes());
			if (image3 != null && image3.getSize() > 0)
				lCarEntity.setImage3(image3.getBytes());
			if (image4 != null && image4.getSize() > 0)
				lCarEntity.setImage4(image4.getBytes());

			Car lUpdatedCarEntity = lCarService.updateCarImage(lCarEntity);
			System.out.println(lUpdatedCarEntity);
			lResponseEntity.setResponseCode(200);
			lResponseEntity.setResponseDescription("Car's images updated successfully");
			lResponseEntity.setId(lUpdatedCarEntity.getCarId());
		} catch (Exception e) {
			e.printStackTrace();
			lResponseEntity.setResponseCode(401);
			lResponseEntity.setResponseDescription(e.getMessage());
		}
		return lResponseEntity;
	}

	@RequestMapping(value = "/getCarById", method = RequestMethod.POST)
	public @ResponseBody Car getCarById(@RequestParam(value = "id", required = false) int carId) {
		System.out.println("Car Id : " + carId);
		Car lCar = null;
		try {
			lCar = lCarService.findByCarId(carId);
			if (lCar == null) {
				lCar = new Car();
				lCar.setResponseCode(401);
				lCar.setResponseDescription("Car data not exist");
			} else {
				lCar.setResponseCode(200);
				lCar.setResponseDescription("success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			lCar = new Car();
			lCar.setResponseCode(401);
			lCar.setResponseDescription(e.getMessage());
		}
		return lCar;
	}

	@RequestMapping(value = "/getCarByUserId", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity getCarByUserId(@RequestParam(value = "id", required = false) int lUserId) {
		System.out.println("User Id : " + lUserId);
		ResponseEntity lResponseEntity = new ResponseEntity();
		List<CarDetailsDTO> lCarDetailsDTOList = new ArrayList<>();
		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			List<Car> lCar = lCarService.findByUserId(lUserId);
			for (Car car : lCar) {
				Availability lAvailability = lAvailabilityService.findByCarId(car.getCarId());
				CarDetailsDTO lCarDetailsDTO = CastToDTOUtility.castCarIntoDTO(car);
				if (lAvailability != null) {
					lCarDetailsDTO.setFromDate(formatter.format(lAvailability.getFromDate()));
					lCarDetailsDTO.setToDate(formatter.format(lAvailability.getToDate()));
					lCarDetailsDTO.setFareAmount(lAvailability.getFareAmount());
				}
				lCarDetailsDTOList.add(lCarDetailsDTO);
			}
			if (lCar.size() == 0) {
				lResponseEntity.setResponseCode(402);
				lResponseEntity.setResponseDescription("Data not found");
				return lResponseEntity;
			}
			lResponseEntity.setResponseCode(200);
			lResponseEntity.setResponseDescription("success");
			lResponseEntity.setCarList(lCarDetailsDTOList);
		} catch (Exception e) {
			e.printStackTrace();
			lResponseEntity.setResponseCode(401);
			lResponseEntity.setResponseDescription(e.getMessage());
		}
		return lResponseEntity;
	}

	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public void test() {
		System.out.println("test");
	}
	
	@RequestMapping(value = "/updateCarVerificationDetails", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity updateCarVerificationDetails(@RequestBody Car lCar) {
		Car lCarEntity;
		ResponseEntity lResponseEntity = new ResponseEntity();
		try {
			if (lCar.getCarId() == 0) {
				throw new Exception("Invalid Car Id");
			}
			lCarEntity = lCarService.updateCarVerificationDetails(lCar);
			System.out.println(lCarEntity);
			lResponseEntity.setResponseCode(200);
			lResponseEntity.setResponseDescription("Car's details updated successfully");
			lResponseEntity.setId(lCarEntity.getCarId());
		} catch (Exception e) {
			e.printStackTrace();
			lResponseEntity.setResponseCode(401);
			lResponseEntity.setResponseDescription(e.getMessage());
		}
		return lResponseEntity;
	}

}

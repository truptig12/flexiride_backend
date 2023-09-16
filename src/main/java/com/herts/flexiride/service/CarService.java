package com.herts.flexiride.service;

import java.util.List;

import com.herts.flexiride.entity.Car;

public interface CarService {

	public Car addCar(Car lCar) throws Exception;

	public Car updateCar(Car lCar) throws Exception;

	public Car updateCarImage(Car lCar) throws Exception;

	public Car findByCarId(int lCarId);

	public List<Car> findByUserId(int lUserId);

	public Car findByLicencePlate(String lLicencePlate);
	
	public Car updateCarVerificationDetails(Car lCar) throws Exception;
}

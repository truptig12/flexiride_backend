package com.herts.flexiride.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.herts.flexiride.entity.Car;
import com.herts.flexiride.repository.CarRepo;
import com.herts.flexiride.service.CarService;

@Service
public class CarImpl implements CarService {

	@Autowired
	CarRepo lCarRepo;

	@Override
	public Car addCar(Car lCar) throws Exception {
		Car lCarEntity = findByLicencePlate(lCar.getLicencePlate());
		System.out.println(lCarEntity);

		if (lCarEntity != null) {
			throw new Exception("Car's Licence Plate details alredy exist");
		}
		return this.lCarRepo.save(lCar);
	}

	@Override
	public Car updateCar(Car lCar) throws Exception {
		Car lCarToUpdate = null;
		Car lCarEntity = findByLicencePlate(lCar.getLicencePlate());
		System.out.println(lCarEntity);
		if (lCarEntity != null) {
			if (lCarEntity.getCarId() != lCar.getCarId())
				throw new Exception("Car's Licence Plate details alredy exist");
		}
		try {
			lCarToUpdate = this.lCarRepo.getById(lCar.getCarId());
			System.out.println("Car To Update : " + lCarToUpdate.toString());
			lCarToUpdate.setUserId(lCar.getUserId());
			lCarToUpdate.setBrand(lCar.getBrand());
			lCarToUpdate.setModel(lCar.getModel());
			lCarToUpdate.setColor(lCar.getColor());
			lCarToUpdate.setManOfYear(lCar.getManOfYear());
			lCarToUpdate.setNoOfSeats(lCar.getNoOfSeats());
			lCarToUpdate.setCountry(lCar.getCountry());
			lCarToUpdate.setCity(lCar.getCity());
			lCarToUpdate.setLicencePlate(lCar.getLicencePlate());
			lCarToUpdate.setFuelType(lCar.getFuelType());
			lCarToUpdate.setGearbox(lCar.getGearbox());
			lCarToUpdate.setEngine(lCar.getEngine());
		} catch (Exception e) {
			throw new Exception("Car data not exist");
		}
		return this.lCarRepo.save(lCarToUpdate);
	}

	@Override
	public Car updateCarImage(Car lCar) throws Exception {
		Car lCarToUpdate = null;
		Car lCarEntity = findByLicencePlate(lCar.getLicencePlate());
		System.out.println(lCarEntity);

		if (lCarEntity != null) {
			throw new Exception("Car details alredy exist");
		}

		try {
			lCarToUpdate = this.lCarRepo.getById(lCar.getCarId());
			System.out.println("Car To Update : " + lCarToUpdate.toString());

			lCarToUpdate.setImage1(lCar.getImage1());
			lCarToUpdate.setImage2(lCar.getImage2());
			lCarToUpdate.setImage3(lCar.getImage3());
			lCarToUpdate.setImage4(lCar.getImage4());

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Car data not exist");
		}
		return this.lCarRepo.save(lCarToUpdate);
	}

	@Override
	public Car findByCarId(int lCarId) {
		System.out.println("In findByCarId");
		Car lCar = null;
		try {
			lCar = this.lCarRepo.findById(lCarId).get();
		} catch (Exception e) {
			return null;
		}
		return lCar;
	}

	@Override
	public List<Car> findByUserId(int lUserId) {
		return this.lCarRepo.findByUserId(lUserId);
	}

	@Override
	public Car findByLicencePlate(String lLicencePlate) {
		return this.lCarRepo.findByLicencePlate(lLicencePlate);
	}
	
	@Override
	public Car updateCarVerificationDetails(Car lCar) throws Exception {
		Car lCarEntity = this.lCarRepo.getById(lCar.getCarId());
		System.out.println(lCarEntity);

		if (lCarEntity == null) {
			throw new Exception("Car data not exist");
		}

		lCarEntity.setBrandVerified(lCar.getBrandVerified());
		lCarEntity.setManOfYearVerified(lCar.getManOfYearVerified());
		lCarEntity.setModelVerified(lCar.getModelVerified());
		lCarEntity.setLicencePlateVerified(lCar.getLicencePlateVerified());

		return this.lCarRepo.save(lCarEntity);
	}

}

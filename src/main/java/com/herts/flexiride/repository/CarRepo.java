package com.herts.flexiride.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.herts.flexiride.entity.Car;

public interface CarRepo extends JpaRepository<Car, Integer> {

	public Car findByLicencePlate(String lLicencePlate);

	public List<Car> findByUserId(int lUserId);
}

package com.herts.flexiride.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.herts.flexiride.entity.Availability;

public interface AvailabilityRepo extends JpaRepository<Availability, Integer> {

	Availability findByCarId(int lCarId);

	@Query(value = "SELECT * FROM availability f WHERE DATE(f.from_date) <= :from_date AND DATE(f.to_date) >= :to_date AND city = :city", nativeQuery = true)
	List<Availability> getAllAvailableCarOnDate(@Param("from_date") Date from_date, @Param("to_date") Date to_date,
			@Param("city") String city);

}
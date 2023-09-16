package com.herts.flexiride.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.herts.flexiride.entity.Availability;
import com.herts.flexiride.repository.AvailabilityRepo;
import com.herts.flexiride.service.AvailabilityService;

@Service
public class AvailabilityImpl implements AvailabilityService {

	@Autowired
	AvailabilityRepo lAvailabilityRepo;

	@Override
	public Availability addAvailability(Availability lAvailability) throws Exception {
		Availability lAvailabilityEntity = findByCarId(lAvailability.getCarId());
		System.out.println(lAvailabilityEntity);

		if (lAvailabilityEntity != null) {
			throw new Exception("Car availability data alredy exist");
		}

		return this.lAvailabilityRepo.save(lAvailability);
	}

	@Override
	public Availability updateAvailability(Availability lAvailability) throws Exception {
		Availability lAvailabilityEntity = findByCarId(lAvailability.getCarId());
		System.out.println(lAvailabilityEntity);

		if (lAvailabilityEntity != null) {
			if (lAvailabilityEntity.getCarId() != lAvailability.getCarId())
				throw new Exception("Car availability data alredy exist");
		}

		try {
			Availability lAvailabilityToUpdate = this.lAvailabilityRepo.getById(lAvailability.getAvailabilityId());
			System.out.println("Car Availability data To Update : " + lAvailabilityToUpdate);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Car availability data not exist");
		}
		return this.lAvailabilityRepo.save(lAvailability);
	}

	@Override
	public Availability findByCarId(int lCarId) {
		Availability lAvailability = null;
		try {
			lAvailability = lAvailabilityRepo.findByCarId(lCarId);
		} catch (Exception e) {
			return null;
		}
		return lAvailability;
	}

	@Override
	public List<Availability> getAllAvailableCarOnDate(String lFromDate, String lToDate, String city) {
		List<Availability> lAvailability = null;
		try {
			lAvailability = lAvailabilityRepo.getAllAvailableCarOnDate(
					new SimpleDateFormat("yyyy-MM-dd").parse(lFromDate),
					new SimpleDateFormat("yyyy-MM-dd").parse(lToDate), city);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return lAvailability;
	}

}

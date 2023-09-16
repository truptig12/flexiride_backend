package com.herts.flexiride.service;

import java.util.List;

import com.herts.flexiride.entity.Availability;

public interface AvailabilityService {

	public Availability addAvailability(Availability lAvailability) throws Exception;

	public Availability updateAvailability(Availability lAvailability) throws Exception;

	public Availability findByCarId(int lCarId);

	public List<Availability> getAllAvailableCarOnDate(String lFromDate, String lToDate, String city);
}

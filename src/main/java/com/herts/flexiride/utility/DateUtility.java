package com.herts.flexiride.utility;

import java.util.Date;

public class DateUtility {

	public static boolean compareDateInFromAndToDate(Date fromDate, Date toDate, Date checkDate) {
		if (fromDate.equals(toDate)) {
			if (fromDate.equals(checkDate))
				return true;
			else
				return false;
		}
		return (checkDate.compareTo(fromDate) >= 0 && checkDate.compareTo(toDate) <= 0);
	}

}

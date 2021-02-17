package com.ygsoft.apps.utilsandwrappers;

import java.util.List;
import java.util.Arrays;
import java.util.Calendar;
import java.text.SimpleDateFormat;


public class DateAndTime {

    private static final List<String> dateFormats = Arrays.asList(
		"yyyyMMdd",
		"yyyy/MM/dd_HH:mm:ss",
		"yyyyMMdd:HHmmss",
		"yyyyMMdd_HHmmss"
    );




	/**
	 * @param formatIndex Between 1 to 4
	 * @return the current date and time in the selected format.
	 */
	public static String getCurrentTime (int formatIndex) {

//		if (formatIndex >= 0 && formatIndex < dateFormats.size()) {
		if (formatIndex < dateFormats.size() && formatIndex >= 0) {

		    Calendar cal = Calendar.getInstance();

			SimpleDateFormat sdf = new SimpleDateFormat(dateFormats.get(formatIndex));

			return sdf.format(cal.getTime());
		}
		else {
			return null;
		}
	}
}


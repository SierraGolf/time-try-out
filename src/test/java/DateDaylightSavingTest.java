

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Test;

@SuppressWarnings("deprecation")
public class DateDaylightSavingTest {
	
	/**
	 * Daylight saving switches from 02:00 to 03:00 on the 11.03.2012 for
	 * Chicago.
	 * 
	 * TIME						DST		UTC offset	Time zone
	 * 1:59:59 AM				No		UTC-6h		CST
	 * 2:00:00 AM → 3:00:00 AM	+1h		UTC-5h		CDT
	 * 3:00:01 AM				+1h		UTC-5h		CDT
	 */
	@Test
	public void testDaylightSavingPeriodStartForOneAmInCST() {
		TimeZone.setDefault(Constants.CST);
		final Date date = new Date(112, 2, 11, 1, 0, 0);
		
		final DateFormat dateFormat = new SimpleDateFormat(Constants.PATTERN);
		dateFormat.setTimeZone(Constants.CST);
		
		assertFalse(Constants.CST.inDaylightTime(date));
		assertEquals(6 * Constants.MINUTES_OF_HOUR, date.getTimezoneOffset());
		assertEquals(Constants.CST.getRawOffset(), -date.getTimezoneOffset() * Constants.MILLIS_OF_MINUTE);
		assertEquals("01:00:00 11.03.2012", dateFormat.format(date));
	}
	
	/**
	 * Daylight saving switches from 02:00 to 03:00 on the 11.03.2012 for
	 * Chicago.
	 * 
	 * TIME						DST		UTC offset	Time zone
	 * 1:59:59 AM				No		UTC-6h		CST
	 * 2:00:00 AM → 3:00:00 AM	+1h		UTC-5h		CDT
	 * 3:00:01 AM				+1h		UTC-5h		CDT
	 */
	@Test
	public void testDaylightSavingPeriodStartForTwoAmInCST() {
		TimeZone.setDefault(Constants.CST);
		final Date date = new Date(112, 2, 11, 2, 0, 0);
		
		final DateFormat dateFormat = new SimpleDateFormat(Constants.PATTERN);
		dateFormat.setTimeZone(Constants.CST);
		
		// actually this function considers the given date to be in the daylight saving period,
		// while it actually does not exist neither in daylight saving time nor in standard time,
		// so it is automatically rolled to 3 am
		assertTrue(Constants.CST.inDaylightTime(date));
		
		// in daylight saving period the date time zone offset is not equal to the time zone
		// raw offset, because it is now one hour off
		assertEquals(5 * Constants.MINUTES_OF_HOUR, date.getTimezoneOffset());
		assertNotSame(Constants.CST.getRawOffset(), -date.getTimezoneOffset() * Constants.MILLIS_OF_MINUTE);
		
		// the time is obviously converted to 3 am
		assertEquals("03:00:00 11.03.2012", dateFormat.format(date));
	}
	
	/**
	 * Daylight saving switches from 02:00 to 03:00 on the 11.03.2012 for
	 * Chicago.
	 * 
	 * TIME						DST		UTC offset	Time zone
	 * 1:59:59 AM				No		UTC-6h		CST
	 * 2:00:00 AM → 3:00:00 AM	+1h		UTC-5h		CDT
	 * 3:00:01 AM				+1h		UTC-5h		CDT
	 */
	@Test
	public void testDaylightSavingPeriodStartForThreeAmInCST() {
		TimeZone.setDefault(Constants.CST);
		final Date date = new Date(112, 2, 11, 3, 0, 0);
		
		final DateFormat dateFormat = new SimpleDateFormat(Constants.PATTERN);
		dateFormat.setTimeZone(Constants.CST);
		
		// this date is actually in daylight saving period
		assertTrue(Constants.CST.inDaylightTime(date));
		
		// in daylight saving period the date time zone offset is not equal to the time zone
		// raw offset, because it is now one hour off
		assertEquals(5 * Constants.MINUTES_OF_HOUR, date.getTimezoneOffset());
		assertNotSame(Constants.CST.getRawOffset(), -date.getTimezoneOffset() * Constants.MILLIS_OF_MINUTE);
		
		// time is as expected
		assertEquals("03:00:00 11.03.2012", dateFormat.format(date));
	}
	
	/**
	 * Daylight saving switches from 02:00 to 01:00 on the 04.11.2012 for
	 * Chicago.
	 * 
	 * TIME						DST		UTC offset	Time zone
	 * 1:59:59 AM				No		UTC-5h		CDT
	 * 2:00:00 AM → 1:00:00 AM	+1h		UTC-6h		CST
	 * 1:00:01 AM				+1h		UTC-6h		CST
	 */
	@Test
	public void testDaylightSavingPeriodEndForMidnightInCST() {
		TimeZone.setDefault(Constants.CST);
		final Date date = new Date(112, 10, 4, 0, 0, 0);
		
		final DateFormat dateFormat = new SimpleDateFormat(Constants.PATTERN);
		dateFormat.setTimeZone(Constants.CST);
		
		// date is not in daylight saving anymore
		assertTrue(Constants.CST.inDaylightTime(date));
		
		// in daylight saving period the date time zone offset is not equal to the time zone
		// raw offset, because it is now one hour off
		assertEquals(5 * Constants.MINUTES_OF_HOUR, date.getTimezoneOffset());
		assertNotSame(Constants.CST.getRawOffset(), -date.getTimezoneOffset() * Constants.MILLIS_OF_MINUTE);
		
		// time is as expected
		assertEquals("00:00:00 04.11.2012", dateFormat.format(date));
	}
	
	/**
	 * Daylight saving switches from 02:00 to 01:00 on the 04.11.2012 for
	 * Chicago.
	 * 
	 * TIME						DST		UTC offset	Time zone
	 * 1:59:59 AM				No		UTC-5h		CDT
	 * 2:00:00 AM → 1:00:00 AM	+1h		UTC-6h		CST
	 * 1:00:01 AM				+1h		UTC-6h		CST
	 */
	@Test
	public void testDaylightSavingPeriodEndForOneAmInCST() {
		TimeZone.setDefault(Constants.CST);
		final Date date = new Date(112, 10, 4, 1, 0, 0);
		
		final DateFormat dateFormat = new SimpleDateFormat(Constants.PATTERN);
		dateFormat.setTimeZone(Constants.CST);

		// obviously it disregards the 1 am of the daylight saving period and
		// silently uses standard time version
		assertFalse(Constants.CST.inDaylightTime(date));
		
		// offset matches the raw offset from UTC, which is 6 hours
		assertEquals(6 * Constants.MINUTES_OF_HOUR, date.getTimezoneOffset());
		assertEquals(Constants.CST.getRawOffset(), -date.getTimezoneOffset() * Constants.MILLIS_OF_MINUTE);
		
		// time is as expected
		assertEquals("01:00:00 04.11.2012", dateFormat.format(date));
	}

	/**
	 * Daylight saving switches from 02:00 to 01:00 on the 04.11.2012 for
	 * Chicago.
	 * 
	 * TIME						DST		UTC offset	Time zone
	 * 1:59:59 AM				No		UTC-5h		CDT
	 * 2:00:00 AM → 1:00:00 AM	+1h		UTC-6h		CST
	 * 1:00:01 AM				+1h		UTC-6h		CST
	 */
	@Test
	public void testDaylightSavingPeriodEndForTwoAmInCST() {
		TimeZone.setDefault(Constants.CST);
		
		// actually this time does not exist in daylight saving
		final Date date = new Date(112, 10, 4, 2, 0, 0);
		
		final DateFormat dateFormat = new SimpleDateFormat(Constants.PATTERN);
		dateFormat.setTimeZone(Constants.CST);
		
		// date is not in daylight saving anymore
		assertFalse(Constants.CST.inDaylightTime(date));
		
		// offset matches the raw offset from UTC, which is 6 hours
		assertEquals(6 * Constants.MINUTES_OF_HOUR, date.getTimezoneOffset());
		assertEquals(Constants.CST.getRawOffset(), -date.getTimezoneOffset() * Constants.MILLIS_OF_MINUTE);
		
		// time is as expected
		assertEquals("02:00:00 04.11.2012", dateFormat.format(date));
	}
}

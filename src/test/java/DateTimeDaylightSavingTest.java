

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

public class DateTimeDaylightSavingTest {
	
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
		final DateTime dateTime = new DateTime(2012, 3, 11, 1, 0, 0, Constants.JODA_CST);
		final DateTimeFormatter formatter = DateTimeFormat.forPattern(Constants.PATTERN);
		
		assertTrue(Constants.JODA_CST.isStandardOffset(dateTime.getMillis()));
		assertEquals(-6 * Constants.MILLIS_OF_HOUR, Constants.JODA_CST.getOffset(dateTime));
		assertEquals("01:00:00 11.03.2012", dateTime.toString(formatter));
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
	@Test(expected = IllegalArgumentException.class)
	public void testDaylightSavingPeriodStartForTwoAmInCST() {
		// this will throw an exception because this instant does not exist in the given time zone
		new DateTime(2012, 3, 11, 2, 0, 0, Constants.JODA_CST);
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
		final DateTime dateTime = new DateTime(2012, 3, 11, 3, 0, 0, Constants.JODA_CST);
		final DateTimeFormatter formatter = DateTimeFormat.forPattern(Constants.PATTERN);
		
		assertFalse(Constants.JODA_CST.isStandardOffset(dateTime.getMillis()));
		assertEquals(-5 * Constants.MILLIS_OF_HOUR, Constants.JODA_CST.getOffset(dateTime));
		assertEquals("03:00:00 11.03.2012", dateTime.toString(formatter));
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
		final DateTime dateTime = new DateTime(2012, 11, 4, 0, 0, 0, Constants.JODA_CST);
		final DateTimeFormatter formatter = DateTimeFormat.forPattern(Constants.PATTERN);
		
		assertFalse(Constants.JODA_CST.isStandardOffset(dateTime.getMillis()));
		assertEquals(-5 * Constants.MILLIS_OF_HOUR, Constants.JODA_CST.getOffset(dateTime));
		assertEquals("00:00:00 04.11.2012", dateTime.toString(formatter));
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
		final DateTime dateTime = new DateTime(2012, 11, 4, 1, 0, 0, Constants.JODA_CST);
		final DateTimeFormatter formatter = DateTimeFormat.forPattern(Constants.PATTERN);
		
		assertFalse(Constants.JODA_CST.isStandardOffset(dateTime.getMillis()));
		assertEquals(-5 * Constants.MILLIS_OF_HOUR, Constants.JODA_CST.getOffset(dateTime));
		assertEquals("01:00:00 04.11.2012", dateTime.toString(formatter));
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
		final DateTime dateTime = new DateTime(2012, 11, 4, 2, 0, 0, Constants.JODA_CST);
		final DateTimeFormatter formatter = DateTimeFormat.forPattern(Constants.PATTERN);
		
		assertTrue(Constants.JODA_CST.isStandardOffset(dateTime.getMillis()));
		assertEquals(-6 * Constants.MILLIS_OF_HOUR, Constants.JODA_CST.getOffset(dateTime));
		assertEquals("02:00:00 04.11.2012", dateTime.toString(formatter));
	}
}

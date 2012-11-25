

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Test;

@SuppressWarnings("deprecation")
public class DateTest {
	
	@Test
	public void testDateConstrutor() {
		// date silently rolls invalid parameters to the next best valid moment
		assertEquals(new Date(85, 31, 11), new Date(87, 7, 11));
	}
	
	@Test
	public void testSimpleDateFormat() {
		TimeZone.setDefault(Constants.GMT);
		final Date date = new Date(85, 5, 22, 12, 0, 0);
		
		assertEquals(0, date.getTimezoneOffset());
		
		final DateFormat dateFormat = new SimpleDateFormat(Constants.PATTERN);
		dateFormat.setTimeZone(Constants.GMT_PLUS_2);
		
		assertEquals("14:00:00 22.06.1985", dateFormat.format(date));
	}
	
	@Test
	public void testDateWithLeapYear() {
		// 29.02.2012
		final Date date = new Date(112, 1, 29, 12, 0, 0);
		final DateFormat dateFormat = new SimpleDateFormat(Constants.PATTERN);

		assertEquals("12:00:00 29.02.2012", dateFormat.format(date));
	}
	
	@Test
	public void testDateWithLeapSecond() {
		final Date date = new Date(112, 5, 30, 23, 59, 60);
		final DateFormat dateFormat = new SimpleDateFormat(Constants.PATTERN);
		
		// no support for leap second, switches automatically to next valid moment in time
		assertEquals("00:00:00 01.07.2012", dateFormat.format(date));
	}
}
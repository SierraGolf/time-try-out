

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

public class CalendarTest {
	
	@Test
	public void testCalendarWithLeapYear() {
		// 29.02.2012
		final Calendar calendar = new GregorianCalendar(2012, 1, 29, 12, 0, 0);
		final DateFormat dateFormat = new SimpleDateFormat(Constants.PATTERN);
		
		// check if the initial value is correct
		assertEquals("12:00:00 29.02.2012", dateFormat.format(calendar.getTime()));

		// check if it add one day correctly
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		assertEquals("12:00:00 01.03.2012", dateFormat.format(calendar.getTime()));

		// check if removing one day works correctly
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		assertEquals("12:00:00 29.02.2012", dateFormat.format(calendar.getTime()));
	}
	
	@Test
	public void testCalendarWithLeapSecond() {
		final Calendar calendar = new GregorianCalendar(2012, 5, 30, 23, 59, 60);
		final DateFormat dateFormat = new SimpleDateFormat(Constants.PATTERN);
		
		// no support for leap second, switches automatically to next valid moment in time
		assertEquals("00:00:00 01.07.2012", dateFormat.format(calendar.getTime()));
	}
}

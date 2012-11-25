

import org.joda.time.DateTime;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

public class DateTimeTest {
	
	@Test(expected = IllegalFieldValueException.class)
	public void testDateTimeWithLeapSecond() {
		final DateTime dateTime = new DateTime(2012, 6, 30, 23, 59, 60);
		final DateTimeFormatter formatter = DateTimeFormat.forPattern(Constants.PATTERN);
		
		// will throw exception, because it does not support leap seconds by default
		dateTime.toString(formatter);
	}
}
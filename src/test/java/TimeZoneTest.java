

import org.junit.Ignore;

import java.util.TimeZone;

import org.joda.time.DateTimeZone;
import org.junit.Test;

public class TimeZoneTest {
	
	@Test
	@Ignore
	public void printTimeZones() {
		for (final String timeZone : TimeZone.getAvailableIDs()) {
			System.out.println(timeZone);
		}
	}
	
	@Test
	@Ignore
	public void printDateTimeZones() {
		for (final String timeZone : DateTimeZone.getAvailableIDs()) {
			System.out.println(timeZone);
		}
	}
}

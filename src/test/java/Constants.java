import java.util.TimeZone;

import org.joda.time.DateTimeZone;


public final class Constants {

	public static final int MINUTES_OF_HOUR = 60;
	public static final int MILLIS_OF_SECOND = 1000;
	public static final int MILLIS_OF_MINUTE = 60 * MILLIS_OF_SECOND;
	public static final int MILLIS_OF_HOUR = 60 * MILLIS_OF_MINUTE;
	public static final String PATTERN = "HH:mm:ss dd.MM.yyyy";
	public static final DateTimeZone JODA_GMT = DateTimeZone.forID("CST6CDT");
	public static final DateTimeZone JODA_CST = DateTimeZone.forID("CST6CDT");
	public static final TimeZone GMT = JODA_GMT.toTimeZone();
	public static final TimeZone CST = JODA_CST.toTimeZone();
	public static final TimeZone GMT_PLUS_2 = TimeZone.getTimeZone("GMT+2");
}

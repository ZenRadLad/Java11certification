package dataTypes;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAdjusters;

public class Dates {
	
	private static final String CONSOLE_SEPARATOR  =  "--------------------------------------";

	public static void main(String[] args) {
		// java.time.*
		// LocalDateTime LocalDate (02/25/2020) + LocalTime (15:20:08.000000001)
					// plusDays, minusMonths, withDays
					// Can be used to compare dates : isBefore isAfter
			// Instant : instant point of time starting from 1/1/1970
					// Instant.EPOCH : start of instant with nanosecond precision
					// Can be used with Duration to increase/decrease s:m:days:months ..
					// Can be used to compare dates : isBefore isAfter
			// TemporalAmounts :amount of time, such as"6 hours", "8 days" or "2 years and 3 months"
					// Period : date based (years, months, days)
					// Duration : time based (hours, minutes, seconds, nanoseconds)
			// TemporalAdjusters :
					// first/last day of month/year, last friday of month/specific date..
//		localDate();
//		localTime();
//		localDateTime();
//		conversions();
//		temporalAmounts();
//		timeAdjusters();	
		timeZones();
	}

	// Only date
	private static void localDate() {
		LocalDate ld = LocalDate.now();
		LocalDate ldOf = LocalDate.of(2022, Month.DECEMBER, 5);

		System.out.println("******* LocalDate ********");
		System.out.println("Current LocalDate = " + ld);
		System.out.println("LocalDate.of(2022, Month.DECEMBER, 5) = " + ldOf);
		System.out.println("LocalDate + 30 days = " + ld.plusDays(30));
		System.out.println("LocalDate + 2 months = " + ld.plusMonths(2));
		System.out.println("LocalDate - 25 years and 11 months = " + ld.minusYears(25).minusMonths(11));
		System.out.println("aLocaleDate.isBefore(anotherLocalDate) = " + ld.isBefore(ldOf));
		System.out.println("aLocaleDate.after(anotherLocalDate) = " + ld.withYear(2023).isAfter(ldOf));
		System.out.println(CONSOLE_SEPARATOR);
	}

	// Only time
	private static void localTime() {
		LocalTime lt = LocalTime.now();
		LocalTime ltOf = LocalTime.of(21, 19);
		
		System.out.println("******* LocalTime ********");
		System.out.println("LocalTime = " + lt);
		System.out.println("LocalTime.of(10,19) =  " + ltOf);
		System.out.println(CONSOLE_SEPARATOR);
	}

	// Date and Time
	private static void localDateTime() {
		LocalDateTime ldt = LocalDateTime.now();
		
		System.out.println("******* LocalDateTime ********");
		System.out.println("LocalDateTime = " + ldt);
		System.out.println("French Date format using DateTimeFormatter : "
				+ DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(ldt));
		System.out.println(CONSOLE_SEPARATOR);
	}

	private static void conversions() {
		LocalDate someDay = LocalDate.of(2025, 02, 14);
		LocalTime someTime = LocalTime.of(00, 59);
		LocalDateTime ldtFromltAndld = someDay.atTime(someTime);
		
		System.out.println("******* LocalDate/LocalDateTime conversions ********");
		System.out.println("LocalDateTime from LocalDate.atTime(LocalTime) = " + ldtFromltAndld);
		System.out.println(CONSOLE_SEPARATOR);
	}
	
	private static void temporalAmounts() {
		
		//Duration = amount of time in nanoseconds
		//Period = amoint of time in units (years, days), used for business logic 
		//Instant = point in time , timestamp, used along with Duration in system tasks (logs..)
		
		Period period = Period.between(LocalDate.parse("2020-01-01"), LocalDate.now());
		
		System.out.println("******* Period/Time/Duration ********");
		System.out.println("Days elapsed since new years eve : " + (period.getMonths() * 30 + period.getDays()));
		
		System.out.println("Duration.ofSeconds(240).toMinutes() : " + Duration.ofSeconds(240).toMinutes());
		System.out.println("Duration.ofDays(3).toHours() * 52 : " +  Duration.ofDays(3).toHours() * 52);
		
		Instant instant = Instant.now();
		System.out.println("Time 25 minutes from now : " + instant.plus(Duration.ofMinutes(25)));
		System.out.println("Time 7 hours ago : " + instant.minus(Duration.ofHours(7)));
		System.out.println(CONSOLE_SEPARATOR);
	}

	private static void timeAdjusters() {
		LocalDate localDate = LocalDate.now();

		LocalDate firstDayOfMonth = localDate.with(TemporalAdjusters.firstDayOfMonth());
		DayOfWeek dof = firstDayOfMonth.getDayOfWeek();

		System.out.println("******* TemporalAdjusters ********");

		System.out.printf("TemporalAdjusters.firstDayOfMonth : %s%n", dof);

		LocalDate lastDayOfMonth = localDate.with(TemporalAdjusters.lastDayOfMonth());
		DayOfWeek dofLm = lastDayOfMonth.getDayOfWeek();
		System.out.printf("TemporalAdjusters.lastDayOfMonth :  %s%n ", dofLm);

		LocalDate nextMondayDate = localDate.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
		System.out.printf("TemporalAdjusters.next(DayOfWeek.MONDAY) == next Monday : %s%n", nextMondayDate);
		System.out.println(CONSOLE_SEPARATOR);
	}
	
	private static void timeZones() {
		ZoneId laZoneId = ZoneId.of("America/Los_Angeles");
		ZoneId gmt4 = ZoneId.of("GMT+2");
		ZoneId systemZone = ZoneId.systemDefault();
		ZonedDateTime laZdt = ZonedDateTime.now(laZoneId);
		
		System.out.println("******* ZonedDateTime ********");
		System.out.println("ZoneId.systemDefault() : " +  systemZone);
		System.out.println("Current ZonedDateTime at Los Angeles : " +  laZdt);
		System.out.println("ZonedDateTime at GMT+6 at the same time at LA (withZoneSameInstant) : " + laZdt.withZoneSameInstant(gmt4));
		System.out.println(CONSOLE_SEPARATOR);
	}
}
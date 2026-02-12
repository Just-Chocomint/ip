package fern;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

/**
 * Class for parsing and formatting dates
 */
public class DateHandler {
    /** Different date formats the user might enter */
    private static final DateTimeFormatter[] FORMATS = {
            new DateTimeFormatterBuilder()
                    .parseCaseInsensitive()
                    .appendPattern("d MMM yyyy")
                    .toFormatter(Locale.ENGLISH),
            new DateTimeFormatterBuilder()
                    .parseCaseInsensitive()
                    .appendPattern("d MMM")
                    .parseDefaulting(java.time.temporal.ChronoField.YEAR,
                            LocalDate.now().getYear())
                    .toFormatter(Locale.ENGLISH),
            new DateTimeFormatterBuilder()
                    .parseCaseInsensitive()
                    .appendPattern("d MMMM yyyy")
                    .parseDefaulting(java.time.temporal.ChronoField.YEAR,
                            LocalDate.now().getYear())
                    .toFormatter(Locale.ENGLISH),
            new DateTimeFormatterBuilder()
                    .parseCaseInsensitive()
                    .appendPattern("d MMMM")
                    .parseDefaulting(java.time.temporal.ChronoField.YEAR,
                            LocalDate.now().getYear())
                    .toFormatter(Locale.ENGLISH),
            DateTimeFormatter.ofPattern("d/M/yyyy"),
    };

    /**
     * Converts string into LocalDate
     * @param userInput Date string to be converted to LocalDate object
     **/
    public static LocalDate stringToDate(String userInput) throws FernException {
        assert userInput != null : "date string should not be null";
//        LocalDate dateTime = LocalDate.now();

        LocalDate dateTime = parseNextWeekday(userInput);
        if (dateTime != null) {
            return dateTime;
        }

        // Loop through the formats to parse user input
        for (DateTimeFormatter format : FORMATS) {
            try {
                dateTime = LocalDate.parse(userInput.trim(), format);
                return dateTime;
            } catch (DateTimeParseException e) {
                // skip to next format to check
            }
        }
        throw new FernException("Date format incorrect, try d/M/yyyy or Day Month Year");
    }

    /**
     * Converts LocalDate object into string
     * @param dateTime LocalDate object to be converted to string
     **/
    public static String dateToString(LocalDate dateTime) throws FernException {
        assert dateTime != null : "Date object shouldnt be null";
        try {
            return dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            throw new FernException("Don't understand date");
        }
    }

    /**
     * Converts weekday string into LocalDate object
     * @param input String to be converted to LocalDate
     **/
    private static LocalDate parseNextWeekday(String input) {
        DayOfWeek day;
        switch (input.toLowerCase()) {
        case "monday":
        case "mon":
            day = DayOfWeek.MONDAY; break;
        case "tuesday":
        case "tue":
            day = DayOfWeek.TUESDAY; break;
        case "wednesday":
        case "wed":
            day = DayOfWeek.WEDNESDAY; break;
        case "thursday":
        case "thu":
            day = DayOfWeek.THURSDAY; break;
        case "friday":
        case "fri":
            day = DayOfWeek.FRIDAY; break;
        case "saturday":
        case "sat":
            day = DayOfWeek.SATURDAY; break;
        case "sunday":
        case "sun":
            day = DayOfWeek.SUNDAY; break;
        default:
            return null;
        }
        return LocalDate.now().with(TemporalAdjusters.next(day));
    }
}

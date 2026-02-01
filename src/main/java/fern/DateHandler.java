package fern;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * Class for parsing and formatting dates
 */
public class DateHandler {
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
        LocalDate dateTime = LocalDate.now();
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
        try {
            return dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            throw new FernException("Don't understand date");
        }
    }
}

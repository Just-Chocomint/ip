package fern;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class DateHandlerTest {
    @Test
    public void stringToDate_validDMY_success() throws FernException {
        LocalDate date = DateHandler.stringToDate("30/01/2026");
        assertEquals(LocalDate.of(2026, 1, 30), date);
    }

    @Test
    public void stringToDate_validDMmmY_success() throws FernException {
        LocalDate date = DateHandler.stringToDate("30 Jan 2026");
        assertEquals(LocalDate.of(2026, 1, 30), date);
    }

    @Test
    public void stringToDate_validDMmm_currentYear() throws FernException {
        int currentYear = LocalDate.now().getYear();
        LocalDate date = DateHandler.stringToDate("30 Jan");
        assertEquals(LocalDate.of(currentYear, 1, 30), date);
    }

    @Test
    public void stringToDate_invalidFormat_throwsException() {
        assertThrows(FernException.class, () -> {
            DateHandler.stringToDate("1/1/1");
        });
    }

    @Test
    public void dateToString_validDate_success() throws FernException {
        LocalDate date = LocalDate.of(2026, 1, 30);
        String str = DateHandler.dateToString(date);
        assertEquals("30/01/2026", str);
    }
}

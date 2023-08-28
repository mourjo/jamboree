package me.mourjo.jamboree.datetime;

import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DatetimeDatetimeFormatTest {
    @Test
    void parse() {
        assertEquals(
                ZonedDateTime.of(1992, 6, 9, 16, 0, 0, 0, ZoneId.of("Etc/UTC")),
                DatetimeFormat.parse("1992-06-09 16:00")
        );

        assertEquals(
                ZonedDateTime.of(1992, 6, 9, 16, 0, 0, 0, ZoneId.of("Etc/UTC")),
                DatetimeFormat.parse("09-06-1992 16:00")
        );

        assertEquals(
                ZonedDateTime.of(1992, 6, 9, 16, 0, 0, 0, ZoneId.of("Etc/UTC")),
                DatetimeFormat.parse("1992-06-09 16:00")
        );

        assertEquals(
                ZonedDateTime.of(1992, 6, 9, 16, 0, 0, 0, ZoneId.of("Etc/UTC")),
                DatetimeFormat.parse("16:00 09 06 1992")
        );

        assertEquals(
                ZonedDateTime.of(1992, 6, 9, 16, 0, 0, 0, ZoneId.of("Etc/UTC")),
                DatetimeFormat.parse("16:00, 09 06 1992")
        );

        assertEquals(
                ZonedDateTime.of(1992, 6, 9, 16, 0, 0, 0, ZoneId.of("Etc/UTC")),
                DatetimeFormat.parse("16:00 09-06-1992")
        );

        assertEquals(
                ZonedDateTime.of(1992, 6, 9, 16, 0, 0, 0, ZoneId.of("Etc/UTC")),
                DatetimeFormat.parse("16:00 09 06 1992")
        );

        assertEquals(
                ZonedDateTime.of(1992, 6, 9, 16, 0, 0, 0, ZoneId.of("Europe/Amsterdam")),
                DatetimeFormat.parse("16:00 09 06 1992 Europe/Amsterdam")
        );

        assertEquals(
                ZonedDateTime.of(1992, 6, 9, 16, 0, 0, 0, ZoneId.of("Asia/Kolkata")),
                DatetimeFormat.parse("16:00, 09 06 1992 Asia/Kolkata")
        );
    }

}
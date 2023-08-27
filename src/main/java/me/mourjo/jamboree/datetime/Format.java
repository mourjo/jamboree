package me.mourjo.jamboree.datetime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class Format {
    static private Logger logger = LoggerFactory.getLogger(Format.class);

    static final DateTimeFormatter unparser = DateTimeFormatter.RFC_1123_DATE_TIME;

    static final List<DateTimeFormatter> FORMATTERS = List.of(
            DateTimeFormatter.RFC_1123_DATE_TIME,
            DateTimeFormatter.BASIC_ISO_DATE,
            DateTimeFormatter.ISO_LOCAL_DATE,
            DateTimeFormatter.ISO_OFFSET_DATE,
            DateTimeFormatter.ISO_DATE,
            DateTimeFormatter.ISO_LOCAL_TIME,
            DateTimeFormatter.ISO_OFFSET_TIME,
            DateTimeFormatter.ISO_TIME,
            DateTimeFormatter.ISO_LOCAL_DATE_TIME,
            DateTimeFormatter.ISO_OFFSET_DATE_TIME,
            DateTimeFormatter.ISO_ZONED_DATE_TIME,
            DateTimeFormatter.ISO_DATE_TIME,
            DateTimeFormatter.ISO_ORDINAL_DATE,
            DateTimeFormatter.ISO_WEEK_DATE,
            DateTimeFormatter.ISO_INSTANT,
            DateTimeFormatter.RFC_1123_DATE_TIME,
            DateTimeFormatter.ofPattern("dd MM yyyy"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"),
            DateTimeFormatter.ofPattern("dd MM yyyy HH:mm")
    );

    public static ZonedDateTime parse(String dateTime) {
        var formatter = FORMATTERS.stream().filter(fmt -> {
            try {
                LocalDateTime.parse(dateTime, fmt);
            } catch (Exception e) {
                return false;
            }
            return true;
        }).findFirst();

        if (formatter.isPresent()) {
            try {
                return ZonedDateTime.parse(dateTime, formatter.get());
            } catch (Exception e) {
                return ZonedDateTime.of(LocalDateTime.parse(dateTime, formatter.get()), ZoneId.of("Etc/UTC"));
            }
        } else {
            logger.warn("Could not parse {}, falling back to current time", dateTime);
            return ZonedDateTime.now();
        }
    }

    public static String unparse(ZonedDateTime ts) {
        return ts.format(unparser);
    }
}

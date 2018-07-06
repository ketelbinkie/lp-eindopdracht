package nl.hanze.application.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ActivePeriodUtil {
    private static final Logger log = LoggerFactory.getLogger(ActivePeriodUtil.class);

    public static java.sql.Date getStartDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return new java.sql.Date(format.parse(determindStartDate()).getTime());
        } catch (ParseException e) {
            log.error("Error: " + e);
        }
        return null;
    }

    public static java.sql.Date getEndDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return new java.sql.Date(format.parse(determindEndtDate()).getTime());
        } catch (ParseException e) {
            log.error("Error: " + e);
        }
        return null;
    }

    private static String determindStartDate() {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        if (month < 7) {
            return year + "-01-01";
        } else {
            return year + "-07-01";
        }
    }

    private static String determindEndtDate() {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        if (month < 7) {
            return year + "-06-30";
        } else {
            return year + "-12-07";
        }
    }

}

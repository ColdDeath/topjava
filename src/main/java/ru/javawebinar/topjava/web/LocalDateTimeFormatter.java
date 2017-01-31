package ru.javawebinar.topjava.web;

import org.springframework.format.Formatter;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Created by ColdDeath&Dummy on 31.01.2017.
 */
public class LocalDateTimeFormatter implements Formatter<LocalDateTime>
{
    @Override
    public LocalDateTime parse(String s, Locale locale) throws ParseException
    {
        if (s == null) {
            return null;
        }
        return DateTimeUtil.parseLocalDateTime(s, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @Override
    public String print(LocalDateTime dateTime, Locale locale) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.toString();
    }
}

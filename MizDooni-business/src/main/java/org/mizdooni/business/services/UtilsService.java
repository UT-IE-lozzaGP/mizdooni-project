package org.mizdooni.business.services;

import org.mizdooni.business.entry.utils.Address;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class UtilsService {
    private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public static Address getAddress(String country, String city, String street) {
        return new Address(country, city, street);
    }
    public static Address getAddress(String country, String city) {
        return new Address(country, city);
    }
    public static LocalTime getTime(String time) {
        return LocalTime.parse(time, timeFormat);
    }
    public static LocalDateTime getDateTime(String time) {
        return LocalDateTime.parse(time, dateTimeFormatter);
    }
    public static String convertTime(LocalTime time) {
        return time.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
    public static String convertDateTime(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}

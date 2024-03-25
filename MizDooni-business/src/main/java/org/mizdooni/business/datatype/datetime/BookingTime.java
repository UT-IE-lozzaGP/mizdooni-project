package org.mizdooni.business.datatype.datetime;


import lombok.NonNull;
import lombok.Value;

import java.time.LocalTime;

@Value
public class BookingTime {
    @NonNull LocalTime value;

    public BookingTime(@NonNull LocalTime value) {
        verify(value);
        this.value = value;
    }

    private static void verify(@NonNull LocalTime value) {
        if (!isBookingValid(value))
            throw new RuntimeException("time slot is not valid");
    }

    private static boolean isBookingValid(@NonNull LocalTime value) {
        return value.getMinute() == 0;
    }
}

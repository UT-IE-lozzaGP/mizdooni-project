package org.mizdooni.business.datatype.datetime;


import lombok.NonNull;
import lombok.Value;

import java.time.LocalTime;

import static org.mizdooni.business.datatype.datetime.DateTimeUtils.isMinutesZero;

@Value
public class BookingTime {
    @NonNull LocalTime startTime;

    public BookingTime(@NonNull LocalTime startTime) {
        verify(startTime);
        this.startTime = startTime;
    }

    private static void verify(@NonNull LocalTime startTime) {
        if (!isMinutesZero(startTime))
            throw new RuntimeException("time slot is not valid");
    }
}

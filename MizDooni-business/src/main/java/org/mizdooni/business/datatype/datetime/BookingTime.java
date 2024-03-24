package org.mizdooni.business.datatype.datetime;


import lombok.NonNull;
import lombok.Value;

import java.time.LocalTime;

@Value
public class BookingTime {
    @NonNull LocalTime startTime;

    public BookingTime(@NonNull LocalTime startTime) {
        verify(startTime);
        this.startTime = startTime;
    }

    private static void verify(@NonNull LocalTime startTime) {
        if (!isSlotValid(startTime)) {
            throw new RuntimeException("time slot is not valid");
        }
    }

    private static boolean isSlotValid(@NonNull LocalTime startTime) {
        return startTime.getMinute() == 0;
    }
}

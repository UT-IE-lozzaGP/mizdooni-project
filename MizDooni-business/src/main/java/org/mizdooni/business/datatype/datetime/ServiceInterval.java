package org.mizdooni.business.datatype.datetime;


import lombok.NonNull;
import lombok.Value;

import java.time.LocalTime;

@Value
public class ServiceInterval {
    @NonNull LocalTime openingTime;

    @NonNull LocalTime closingTime;

    public ServiceInterval(@NonNull LocalTime openingTime, @NonNull LocalTime closingTime) {
        verify(openingTime, closingTime);

        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    private static void verify(@NonNull LocalTime openingTime, @NonNull LocalTime closingTime) {
        if (!isIntervalValid(openingTime, closingTime))
            throw new RuntimeException("service interval is not valid");
    }

    private static boolean isIntervalValid(@NonNull LocalTime openingTime, @NonNull LocalTime closingTime) {
        return openingTime.getMinute() == 0
                && closingTime.getMinute() == 0
                && openingTime.isBefore(closingTime);
    }
}

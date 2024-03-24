package org.mizdooni.business.datatype.datetime;


import lombok.NonNull;
import lombok.Value;

import java.time.LocalTime;

import static org.mizdooni.business.datatype.datetime.DateTimeUtils.isMinutesZero;

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
        return isMinutesZero(openingTime)
                && isMinutesZero(closingTime)
                && openingTime.isBefore(closingTime);
    }
}

package org.mizdooni.business.datatype.datetime;


import lombok.NonNull;
import lombok.Value;

@Value
public class ServiceInterval {
    @NonNull Integer startHour;

    @NonNull Integer endHour;

    public ServiceInterval(@NonNull Integer startHour, @NonNull Integer endHour) {
        verify(startHour, endHour);

        this.startHour = startHour;
        this.endHour = endHour;
    }

    private static void verify(@NonNull Integer startHour, @NonNull Integer endHour) {
        if (!isIntervalValid(startHour, endHour)) {
            throw new RuntimeException("service interval is not valid");
        }
    }

    private static boolean isIntervalValid(@NonNull Integer startHour, @NonNull Integer endHour) {
        return 0 <= startHour && startHour < 24
                && 0 < endHour && endHour <= 24
                && startHour <= endHour;
    }
}

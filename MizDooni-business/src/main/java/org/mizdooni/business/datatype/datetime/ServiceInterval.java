package org.mizdooni.business.datatype.datetime;


import lombok.*;
import org.mizdooni.business.datatype.Message;
import org.mizdooni.business.exception.Exception;

@EqualsAndHashCode
@Getter
@Builder
public class ServiceInterval {
    @NonNull
    private final Integer startHour;

    @NonNull
    private final Integer endHour;

    public ServiceInterval(@NonNull Integer startHour, @NonNull Integer endHour) {
        verifyInterval(startHour, endHour);

        this.startHour = startHour;
        this.endHour = endHour;
    }

    public static boolean isIntervalValid(@NonNull Integer startHour, @NonNull Integer endHour) {
        return 0 <= startHour && startHour < 24
                && 0 < endHour && endHour <= 24
                && startHour <= endHour;
    }

    private static void verifyInterval(@NonNull Integer startHour, @NonNull Integer endHour) {
        if (!isIntervalValid(startHour, endHour)) {
            Exception exception = new Exception();
            exception.addMessage(new Message("service interval is not valid"));
            throw exception;
        }
    }
}

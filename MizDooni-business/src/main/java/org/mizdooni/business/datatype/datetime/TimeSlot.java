package org.mizdooni.business.datatype.datetime;


import lombok.*;
import org.mizdooni.business.datatype.string.Message;
import org.mizdooni.business.exception.Exception;

@Data
public class TimeSlot {
    @NonNull
    private final Integer startHour;

    public TimeSlot(@NonNull Integer startHour) {
        verifySlot(startHour);

        this.startHour = startHour;
    }

    public static boolean isSlotValid(@NonNull Integer startHour) {
        return 0 <= startHour && startHour < 24;
    }

    private static void verifySlot(@NonNull Integer startHour) {
        if (!isSlotValid(startHour)) {
            Exception exception = new Exception();
            exception.addMessage(new Message("time slot is not valid"));
            throw exception;
        }
    }
}

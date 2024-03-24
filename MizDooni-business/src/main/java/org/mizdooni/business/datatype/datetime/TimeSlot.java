package org.mizdooni.business.datatype.datetime;


import lombok.NonNull;
import lombok.Value;

@Value
public class TimeSlot {
    @NonNull Integer startHour;

    public TimeSlot(@NonNull Integer startHour) {
        verify(startHour);

        this.startHour = startHour;
    }

    private static void verify(@NonNull Integer startHour) {
        if (!isSlotValid(startHour)) {
            throw new RuntimeException("time slot is not valid");
        }
    }

    private static boolean isSlotValid(@NonNull Integer startHour) {
        return 0 <= startHour && startHour < 24;
    }
}

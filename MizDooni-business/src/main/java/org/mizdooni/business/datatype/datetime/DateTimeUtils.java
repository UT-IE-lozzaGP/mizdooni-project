package org.mizdooni.business.datatype.datetime;

import lombok.NonNull;

import java.time.LocalTime;

class DateTimeUtils {
    static boolean isMinutesZero(@NonNull LocalTime time) {
        return time.getMinute() == 0;
    }
}

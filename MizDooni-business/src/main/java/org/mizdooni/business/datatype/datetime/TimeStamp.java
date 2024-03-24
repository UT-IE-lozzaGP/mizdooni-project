package org.mizdooni.business.datatype.datetime;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@NoArgsConstructor
public class TimeStamp {
    @NonNull LocalDateTime dateTime = LocalDateTime.now();
}

package org.mizdooni.business.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.mizdooni.business.datatype.ID;
import org.mizdooni.business.datatype.datetime.TimeSlot;

@Builder
@Getter
public class Reservation {
    @NonNull
    @Builder.Default
    private final ID id = new ID();

    @NonNull
    private ID customerID;

    @NonNull
    private ID tableID;

    @NonNull
    private TimeSlot timeSlot;

    public Reservation(@NonNull ID customerID,
                       @NonNull ID tableID,
                       @NonNull TimeSlot timeSlot) {
        this.customerID = customerID;
        this.tableID = tableID;
        this.timeSlot = timeSlot;
    }

}

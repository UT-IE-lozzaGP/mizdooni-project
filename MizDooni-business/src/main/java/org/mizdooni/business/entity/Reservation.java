package org.mizdooni.business.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.mizdooni.business.datatype.datetime.TimeSlot;
import org.mizdooni.business.datatype.id.CustomerID;
import org.mizdooni.business.datatype.id.ReservationID;
import org.mizdooni.business.datatype.id.TableID;

@Builder
@Getter
public class Reservation {
    @NonNull
    @Builder.Default
    private final ReservationID id = new ReservationID();

    @NonNull
    private CustomerID customerID;

    @NonNull
    private TableID tableID;

    @NonNull
    private TimeSlot timeSlot;

    public Reservation(@NonNull CustomerID customerID,
                       @NonNull TableID tableID,
                       @NonNull TimeSlot timeSlot) {
        this.customerID = customerID;
        this.tableID = tableID;
        this.timeSlot = timeSlot;
    }

}

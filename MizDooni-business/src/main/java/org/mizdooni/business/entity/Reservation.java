package org.mizdooni.business.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.mizdooni.business.datatype.datetime.TimeSlot;
import org.mizdooni.business.datatype.id.ReservationID;
import org.mizdooni.business.datatype.id.TableID;
import org.mizdooni.business.datatype.id.user.CustomerID;

@Getter
@Setter
public class Reservation {
    @NonNull
    private final ReservationID id = new ReservationID();

    @NonNull
    private CustomerID customerID;

    @NonNull
    private TableID tableID;

    @NonNull
    private TimeSlot timeSlot;

    @Builder
    public Reservation(@NonNull CustomerID customerID,
                       @NonNull TableID tableID,
                       @NonNull TimeSlot timeSlot) {
        this.customerID = customerID;
        this.tableID = tableID;
        this.timeSlot = timeSlot;
    }

}

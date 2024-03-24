package org.mizdooni.business.entity;

import lombok.*;
import org.mizdooni.business.datatype.id.RestaurantID;
import org.mizdooni.business.datatype.id.TableID;
import org.mizdooni.business.datatype.integer.SeatCount;
import org.mizdooni.business.datatype.integer.TableNumber;

@Getter
public class Table {
    @NonNull
    private final TableID id = new TableID();

    @NonNull
    private final RestaurantID restaurantID;

    @NonNull @Setter
    private TableNumber tableNumber;

    @NonNull @Setter
    private SeatCount seatCount;

    @Builder
    public Table(@NonNull RestaurantID restaurantID,
                 @NonNull TableNumber tableNumber,
                 @NonNull SeatCount seatCount) {
        this.restaurantID = restaurantID;
        this.tableNumber = tableNumber;
        this.seatCount = seatCount;
    }
}

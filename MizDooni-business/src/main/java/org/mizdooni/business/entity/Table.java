package org.mizdooni.business.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.mizdooni.business.datatype.id.RestaurantID;
import org.mizdooni.business.datatype.id.TableID;

@Builder
@Getter
public class Table {
    @NonNull
    @Builder.Default
    private final TableID id = new TableID();

    @NonNull
    private final RestaurantID restaurantID;

    @NonNull @Setter
    private TableNumber tableNumber;

    @NonNull @Setter
    private SeatsCount seatsCount;

    public Table(@NonNull RestaurantID restaurantID,
                 @NonNull TableNumber tableNumber,
                 @NonNull SeatsCount seatsCount) {
        this.restaurantID = restaurantID;
        this.tableNumber = tableNumber;
        this.seatsCount = seatsCount;
    }
}

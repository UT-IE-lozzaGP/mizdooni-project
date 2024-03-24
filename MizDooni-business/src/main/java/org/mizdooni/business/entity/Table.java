package org.mizdooni.business.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.mizdooni.business.datatype.ID;

@Builder
@Getter
public class Table {
    @NonNull
    @Builder.Default
    private final ID id = new ID();

    @NonNull
    private final ID restaurantID;

    @NonNull @Setter
    private Integer tableNumber;

    @NonNull @Setter
    private Integer seatsNumber;

    public Table(@NonNull ID restaurantID,
                 @NonNull Integer tableNumber,
                 @NonNull Integer seatsNumber) {
        this.restaurantID = restaurantID;
        this.tableNumber = tableNumber;
        this.seatsNumber = seatsNumber;
    }
}

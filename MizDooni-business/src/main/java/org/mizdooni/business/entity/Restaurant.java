package org.mizdooni.business.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.mizdooni.business.datatype.*;
import org.mizdooni.business.datatype.datetime.ServiceInterval;
import org.mizdooni.business.datatype.id.ManagerID;
import org.mizdooni.business.datatype.id.RestaurantID;

@Builder
@Getter
public class Restaurant {
    @NonNull
    @Builder.Default
    private final RestaurantID id = new RestaurantID();

    @NonNull @Setter
    private Name name;

    @NonNull @Setter
    private ManagerID managerID;

    @NonNull @Setter
    private FoodCategory foodCategory;

    @NonNull @Setter
    private Description description;

    @NonNull @Setter
    private ServiceInterval serviceInterval;

    @NonNull @Setter
    private Address address;

    public Restaurant(@NonNull Name name,
                      @NonNull ManagerID managerID,
                      @NonNull FoodCategory foodCategory,
                      @NonNull Description description,
                      @NonNull ServiceInterval serviceInterval,
                      @NonNull Address address) {
        this.name = name;
        this.managerID = managerID;
        this.foodCategory = foodCategory;
        this.description = description;
        this.serviceInterval = serviceInterval;
        this.address = address;
    }
}

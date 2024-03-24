package org.mizdooni.business.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.mizdooni.business.datatype.Address;
import org.mizdooni.business.datatype.datetime.ServiceInterval;
import org.mizdooni.business.datatype.id.RestaurantID;
import org.mizdooni.business.datatype.id.user.ManagerID;
import org.mizdooni.business.datatype.string.Description;
import org.mizdooni.business.datatype.string.FoodCategory;
import org.mizdooni.business.datatype.string.Name;

@Getter
@Setter
public class Restaurant {
    @NonNull
    private final RestaurantID id = new RestaurantID();

    @NonNull
    private Name name;

    @NonNull
    private ManagerID managerID;

    @NonNull
    private FoodCategory foodCategory;

    @NonNull
    private Description description;

    @NonNull
    private ServiceInterval serviceInterval;

    @NonNull
    private Address address;

    @Builder
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

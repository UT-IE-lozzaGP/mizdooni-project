package org.mizdooni.business.entity;

import lombok.*;
import org.mizdooni.business.datatype.*;
import org.mizdooni.business.datatype.datetime.ServiceInterval;
import org.mizdooni.business.entity.user.Manager;

@Builder
@Getter
@EqualsAndHashCode
public class Restaurant {
    @NonNull
    @Builder.Default
    private final ID id = new ID();

    @NonNull @Setter
    private Name name;

    @NonNull @Setter
    private Manager manager;

    @NonNull @Setter
    private FoodCategory foodCategory;

    @NonNull @Setter
    private Description description;

    @NonNull @Setter
    private ServiceInterval serviceInterval;

    @NonNull @Setter
    private Address address;

    public Restaurant(@NonNull Name name,
                      @NonNull Manager manager,
                      @NonNull FoodCategory foodCategory,
                      @NonNull Description description,
                      @NonNull ServiceInterval serviceInterval,
                      @NonNull Address address) {
        this.name = name;
        this.manager = manager;
        this.foodCategory = foodCategory;
        this.description = description;
        this.serviceInterval = serviceInterval;
        this.address = address;
    }
}

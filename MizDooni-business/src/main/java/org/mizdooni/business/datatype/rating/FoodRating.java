package org.mizdooni.business.datatype.rating;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class FoodRating extends Rating {
    public FoodRating(@NonNull Float rating) {
        super(rating);
    }
}

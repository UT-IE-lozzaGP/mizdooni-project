package org.mizdooni.business.datatype.rating;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class OverallRating extends Rating {
    public OverallRating(@NonNull Float rating) {
        super(rating);
    }
}

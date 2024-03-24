package org.mizdooni.business.datatype.rating;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class AmbienceRating extends Rating {
    public AmbienceRating(@NonNull Float rating) {
        super(rating);
    }
}

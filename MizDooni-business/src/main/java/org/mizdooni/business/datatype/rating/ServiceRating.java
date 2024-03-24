package org.mizdooni.business.datatype.rating;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class ServiceRating extends Rating {
    public ServiceRating(@NonNull Float rating) {
        super(rating);
    }
}

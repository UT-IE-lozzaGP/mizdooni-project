package org.mizdooni.business.datatype;

import lombok.*;
import org.mizdooni.business.datatype.rating.AmbienceRating;
import org.mizdooni.business.datatype.rating.FoodRating;
import org.mizdooni.business.datatype.rating.OverallRating;
import org.mizdooni.business.datatype.rating.ServiceRating;

@Value
@Builder
@AllArgsConstructor
public class RatingCollection {
    @NonNull FoodRating foodRating;

    @NonNull ServiceRating serviceRating;

    @NonNull AmbienceRating ambienceRating;

    @NonNull OverallRating overallRating;
}

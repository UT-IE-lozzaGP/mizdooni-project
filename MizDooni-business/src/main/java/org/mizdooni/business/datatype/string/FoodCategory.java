package org.mizdooni.business.datatype.string;

import lombok.*;

@EqualsAndHashCode
@Getter
@AllArgsConstructor
@Builder
public class FoodCategory {
    @NonNull
    private String value;
}

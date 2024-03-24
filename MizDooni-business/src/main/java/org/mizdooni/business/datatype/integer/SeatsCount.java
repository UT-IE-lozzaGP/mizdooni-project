package org.mizdooni.business.datatype.integer;

import lombok.*;

@EqualsAndHashCode
@Getter
@AllArgsConstructor
@Builder
public class SeatsCount {
    @NonNull
    private Integer value;
}

package org.mizdooni.business.datatype.integer;

import lombok.*;

@EqualsAndHashCode
@Getter
@AllArgsConstructor
@Builder
public class TableNumber {
    @NonNull
    private Integer value;
}

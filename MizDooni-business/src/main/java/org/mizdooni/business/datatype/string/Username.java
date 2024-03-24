package org.mizdooni.business.datatype.string;

import lombok.*;

@EqualsAndHashCode
@Getter
@AllArgsConstructor
@Builder
public class Username {
    @NonNull
    private String value;
}

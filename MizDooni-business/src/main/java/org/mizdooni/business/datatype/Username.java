package org.mizdooni.business.datatype;

import lombok.*;

@EqualsAndHashCode
@Getter
@AllArgsConstructor
@Builder
public class Username {
    @NonNull
    private String value;
}

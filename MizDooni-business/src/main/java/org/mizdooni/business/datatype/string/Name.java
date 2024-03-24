package org.mizdooni.business.datatype.string;

import lombok.*;

@EqualsAndHashCode
@Getter
@AllArgsConstructor
@Builder
public class Name {
    @NonNull
    private String value;
}

package org.mizdooni.business.datatype.string;

import lombok.*;

@EqualsAndHashCode
@Getter
@AllArgsConstructor
@Builder
public class Description {
    @NonNull
    private String value;
}

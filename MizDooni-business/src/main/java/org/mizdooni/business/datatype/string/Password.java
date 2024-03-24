package org.mizdooni.business.datatype.string;

import lombok.*;

@EqualsAndHashCode
@Getter
@AllArgsConstructor
@Builder
public class Password {
    @NonNull
    private String value;
}

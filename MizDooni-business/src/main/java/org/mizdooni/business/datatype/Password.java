package org.mizdooni.business.datatype;

import lombok.*;

@EqualsAndHashCode
@Getter
@AllArgsConstructor
@Builder
public class Password {
    @NonNull
    private String value;
}

package org.mizdooni.business.datatype;

import lombok.*;

@EqualsAndHashCode
@Getter
@AllArgsConstructor
@Builder
public class Email {
    @NonNull
    private String value;
}

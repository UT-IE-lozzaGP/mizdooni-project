package org.mizdooni.business.datatype;

import lombok.*;

@EqualsAndHashCode
@Getter
@AllArgsConstructor
@Builder
public class Message {
    @NonNull
    private String value;
}

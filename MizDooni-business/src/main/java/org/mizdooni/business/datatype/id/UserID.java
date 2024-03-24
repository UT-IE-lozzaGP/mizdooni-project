package org.mizdooni.business.datatype.id;

import lombok.*;
import lombok.experimental.NonFinal;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder
@Value
@NonFinal
public class UserID extends ID {
}

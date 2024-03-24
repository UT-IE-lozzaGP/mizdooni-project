package org.mizdooni.business.datatype;

import lombok.*;

@Builder
@AllArgsConstructor
@Data
public class Address {
    @NonNull
    private final String country;

    @NonNull
    private final String city;

    @NonNull
    @Builder.Default
    private final String street = "";

}

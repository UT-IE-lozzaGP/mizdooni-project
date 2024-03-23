package org.mizdooni.business.datatype;

import lombok.*;

@EqualsAndHashCode
@Getter
@AllArgsConstructor
@Builder
public class Address {
    @NonNull
    private final String country;

    @NonNull
    private final String city;

    @NonNull
    @Builder.Default
    private final String street = "";

    public Address(@NonNull String country, @NonNull String city) {
        this(country, city, "");
    }
}
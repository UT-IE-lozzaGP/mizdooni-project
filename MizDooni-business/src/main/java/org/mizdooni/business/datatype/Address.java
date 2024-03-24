package org.mizdooni.business.datatype;

import lombok.*;

@Builder
@Value
public class Address {
    @NonNull String country;

    @NonNull String city;

    @Builder.Default
    @NonNull String street = "";

}

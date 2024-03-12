package org.lozza.business.ds.utils;

import java.util.Objects;

public record Address(
        String country,
        String city,
        String street
) {
    public Address(String country, String city) {
        this(country, city, null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(country, address.country) && Objects.equals(city, address.city) && Objects.equals(street, address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city, street);
    }
}

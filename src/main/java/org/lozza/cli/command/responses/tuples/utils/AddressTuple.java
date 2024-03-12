package org.lozza.cli.command.responses.tuples.utils;

public record AddressTuple(
        String country,
        String city,
        String street) {
    public AddressTuple(String country, String city) {
        this(country, city, null);
    }

}

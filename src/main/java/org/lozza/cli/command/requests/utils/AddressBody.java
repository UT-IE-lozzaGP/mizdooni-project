package org.lozza.cli.command.requests.utils;

public record AddressBody(
        String country,
        String city,
        String street) {
    public AddressBody(String country, String city) {
        this(country, city, null);
    }
}

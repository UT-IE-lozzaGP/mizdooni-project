package org.lozza.cli.command.requests;

public record AddTableBody(
        Integer tableNumber,
        String restaurantName,
        String managerUsername,
        Integer seatsNumber) {
}

package org.lozza.cli.command.requests;

public record CancelReservationBody(
        String username,
        Integer reservationNumber) {
}

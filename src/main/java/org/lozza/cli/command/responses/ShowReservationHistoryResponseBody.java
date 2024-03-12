package org.lozza.cli.command.responses;

import org.lozza.cli.command.responses.tuples.ReservationHistoryTuple;

import java.util.List;

public record ShowReservationHistoryResponseBody(List<ReservationHistoryTuple> reservationHistory) {
}

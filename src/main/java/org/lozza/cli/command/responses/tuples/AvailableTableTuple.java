package org.lozza.cli.command.responses.tuples;

import java.util.List;

public record AvailableTableTuple(
        int tableNumber,
        int seatsNumber,
        List<String> availableTimes) {
}

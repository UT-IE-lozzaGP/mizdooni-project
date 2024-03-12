package org.lozza.cli.command.responses;

import org.lozza.cli.command.responses.tuples.AvailableTableTuple;

import java.util.List;

public record ShowAvailableTablesResponseBody(List<AvailableTableTuple> availableTables) {
}

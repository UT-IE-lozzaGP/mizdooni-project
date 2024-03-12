package org.lozza.cli.command.responses;

import org.lozza.cli.command.responses.tuples.FoundRestaurantTuple;

import java.util.List;

public record SearchRestaurantByNameResponseBody(List<FoundRestaurantTuple> restaurants) {
}

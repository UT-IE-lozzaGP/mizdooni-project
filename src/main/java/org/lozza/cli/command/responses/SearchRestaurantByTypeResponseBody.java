package org.lozza.cli.command.responses;

import org.lozza.cli.command.responses.tuples.FoundRestaurantTuple;

import java.util.List;

public record SearchRestaurantByTypeResponseBody(List<FoundRestaurantTuple> restaurants) {
}

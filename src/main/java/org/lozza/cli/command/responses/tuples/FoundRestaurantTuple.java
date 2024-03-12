package org.lozza.cli.command.responses.tuples;

import org.lozza.cli.command.responses.tuples.utils.AddressTuple;

public record FoundRestaurantTuple(
        String name,
        String type,
        String startTime,
        String endTime,
        String description,
        AddressTuple address
) {
}

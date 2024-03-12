package org.lozza.cli.command.requests;

import org.lozza.cli.command.requests.utils.AddressBody;

public record AddRestaurantBody(
        String name,
        String managerUsername,
        String type,
        String startTime,
        String endTime,
        String description,
        AddressBody address) {
}

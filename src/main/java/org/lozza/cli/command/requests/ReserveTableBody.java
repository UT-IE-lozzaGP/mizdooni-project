package org.lozza.cli.command.requests;

public record ReserveTableBody(
        String username,
        String restaurantName,
        int tableNumber,
        String datetime) {
}

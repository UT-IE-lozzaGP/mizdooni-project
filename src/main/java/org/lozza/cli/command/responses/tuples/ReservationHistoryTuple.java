package org.lozza.cli.command.responses.tuples;

import java.time.LocalDateTime;

public record ReservationHistoryTuple(
        int reservationNumber,
        String restaurantName,
        int tableNumber,
        LocalDateTime dataTime
) {
}

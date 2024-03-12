package org.lozza.cli.command;

import org.lozza.business.services.*;
import org.lozza.cli.command.requests.ReserveTableBody;
import org.lozza.cli.command.responses.ReserveTableResponseBody;
import org.lozza.cli.command.responses.Response;

public record ReserveTableCommand(ReserveTableBody body) implements Command {
    @Override
    public Response run() {
        try {
            int reservationNumber = ReservationService.reserveTable(
                    UserService.getClientByForce(body.username()),
                    TableService.getTableByForce(
                            RestaurantService.getRestaurantByForce(body().restaurantName()),
                            body().tableNumber()
                    ),
                    UtilsService.getDateTime(body.datetime())
            );
            return new Response(true, new ReserveTableResponseBody(reservationNumber));
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "AddRestaurantCommand{" +
                "body=" + body +
                '}';
    }
}

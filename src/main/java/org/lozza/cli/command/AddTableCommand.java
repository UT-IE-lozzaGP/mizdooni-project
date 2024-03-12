package org.lozza.cli.command;

import org.lozza.business.services.RestaurantService;
import org.lozza.business.services.TableService;
import org.lozza.business.services.UserService;
import org.lozza.cli.command.requests.AddTableBody;
import org.lozza.cli.command.responses.Response;

public record AddTableCommand(AddTableBody body) implements Command {
    @Override
    public Response run() {
        try {
            TableService.addTable(
                    RestaurantService.getRestaurantByForce(body().restaurantName()),
                    UserService.getManagerByForce(body().managerUsername()),
                    body.tableNumber(),
                    body().seatsNumber()
            );
            return new Response(true, "Table added successfully.");
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "AddTableCommand{" +
                "body=" + body +
                '}';
    }
}

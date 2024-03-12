package org.lozza.cli.command;

import org.lozza.business.services.UserService;
import org.lozza.business.services.UtilsService;
import org.lozza.cli.command.responses.Response;
import org.lozza.cli.command.requests.AddRestaurantBody;
import org.lozza.business.services.RestaurantService;

public record AddRestaurantCommand(AddRestaurantBody body) implements Command {
    @Override
    public Response run() {
        try {
            RestaurantService.addRestaurant(
                    body.name(),
                    UserService.getManagerByForce(body.managerUsername()),
                    body.type(),
                    body.description(),
                    UtilsService.getTime(body.startTime()),
                    UtilsService.getTime(body.endTime()),
                    UtilsService.getAddress(
                            body.address().country(),
                            body.address().city(),
                            body.address().street()
                    )
            );
            return new Response(true, "Restaurant added successfully.");
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

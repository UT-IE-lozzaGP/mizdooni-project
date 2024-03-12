package org.lozza.cli.command;

import org.lozza.business.services.RestaurantService;
import org.lozza.business.services.ReviewService;
import org.lozza.business.services.UserService;
import org.lozza.cli.command.requests.AddReviewBody;
import org.lozza.cli.command.responses.Response;

public record AddReviewCommand(AddReviewBody body) implements Command {
    @Override
    public Response run() {
        try {
            ReviewService.addReview(
                    UserService.getClientByForce(body.username()),
                    RestaurantService.getRestaurantByForce(body.restaurantName()),
                    body.foodRate(),
                    body.serviceRate(),
                    body.ambianceRate(),
                    body.overallRate(),
                    body.comment()
            );
            return new Response(true, "Review added successfully.");
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "AddReviewCommand{" +
                "body=" + body +
                '}';
    }
}

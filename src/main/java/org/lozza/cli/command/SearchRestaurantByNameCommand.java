package org.lozza.cli.command;

import org.lozza.business.ds.Restaurant;
import org.lozza.business.services.RestaurantService;
import org.lozza.business.services.UtilsService;
import org.lozza.cli.command.requests.SearchRestaurantByNameBody;
import org.lozza.cli.command.responses.Response;
import org.lozza.cli.command.responses.SearchRestaurantByNameResponseBody;
import org.lozza.cli.command.responses.tuples.FoundRestaurantTuple;
import org.lozza.cli.command.responses.tuples.utils.AddressTuple;

import java.util.List;

public record SearchRestaurantByNameCommand(SearchRestaurantByNameBody body) implements Command {
    @Override
    public Response run() {
        try {
            List<Restaurant> restaurants = RestaurantService.searchRestaurantByName(
                    body.name()
            );
            return new Response(true, new SearchRestaurantByNameResponseBody(
                    restaurants.stream()
                            .map(restaurant -> new FoundRestaurantTuple(
                                    restaurant.name(),
                                    restaurant.type(),
                                    UtilsService.convertTime(restaurant.startTime()),
                                    UtilsService.convertTime(restaurant.endTime()),
                                    restaurant.description(),
                                    new AddressTuple(
                                            restaurant.address().country(),
                                            restaurant.address().city(),
                                            restaurant.address().street()))
                            ).toList()
            ));
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "SearchRestaurantByNameCommand{" +
                "body=" + body +
                '}';
    }
}

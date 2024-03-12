package org.lozza.cli.command;

import org.lozza.business.ds.Restaurant;
import org.lozza.business.services.RestaurantService;
import org.lozza.business.services.UtilsService;
import org.lozza.cli.command.requests.SearchRestaurantByTypeBody;
import org.lozza.cli.command.responses.Response;
import org.lozza.cli.command.responses.SearchRestaurantByTypeResponseBody;
import org.lozza.cli.command.responses.tuples.FoundRestaurantTuple;
import org.lozza.cli.command.responses.tuples.utils.AddressTuple;

import java.util.List;

public record SearchRestaurantByTypeCommand(SearchRestaurantByTypeBody body) implements Command {
    @Override
    public Response run() {
        try {
            List<Restaurant> restaurants = RestaurantService.searchRestaurantByType(
                    body.type()
            );
            return new Response(true, new SearchRestaurantByTypeResponseBody(
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

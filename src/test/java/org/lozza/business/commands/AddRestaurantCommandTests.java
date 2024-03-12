package org.lozza.business.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.lozza.cli.command.requests.utils.AddressBody;
import org.lozza.cli.command.requests.AddRestaurantBody;
import org.lozza.cli.CLI;
import org.lozza.cli.command.Command;

public class AddRestaurantCommandTests {
    @Test
    public void addRestaurantBodyTest1() {
        Assertions.assertDoesNotThrow(() -> {
            final Command command = CLI.getCommandFromInput("addRestaurant {" +
                    "\"name\": \"restaurant1\", " +
                    "\"managerUsername\": \"user2\", " +
                    "\"type\": \"Iranian\"," +
                    "\"startTime\": \"08:00\", " +
                    "\"endTime\": \"23:00\", " +
                    "\"description\": \"Open seven days a week\"," +
                    "\"address\": {" +
                    "   \"country\": \"Iran\", " +
                    "   \"city\": \"Tehran\", " +
                    "   \"street\": \"North Kargar\"" +
                    "}}");

            final AddRestaurantBody body = (AddRestaurantBody) command.body();
            Assertions.assertEquals(new AddRestaurantBody(
                    "restaurant1",
                    "user2",
                    "Iranian",
                    "08:00",
                    "23:00",
                    "Open seven days a week",
                    new AddressBody("Iran", "Tehran", "North Kargar")
            ), body);
        });
    }
}

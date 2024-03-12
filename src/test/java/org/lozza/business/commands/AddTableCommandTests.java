package org.lozza.business.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.lozza.cli.command.requests.AddTableBody;
import org.lozza.cli.CLI;
import org.lozza.cli.command.Command;

public class AddTableCommandTests {
    @Test
    public void addTableTest1() {
        Assertions.assertDoesNotThrow(() -> {
            final Command command = CLI.getCommandFromInput("addTable {" +
                    "\"tableNumber\": 1, " +
                    "\"restaurantName\": \"restaurant1\", " +
                    "\"managerUsername\": \"user2\", " +
                    "\"seatsNumber\": 4}");

            final AddTableBody body = (AddTableBody) command.body();
            Assertions.assertEquals(new AddTableBody(
                    1,
                    "restaurant1",
                    "user2",
                    4
            ), body);
        });
    }
}

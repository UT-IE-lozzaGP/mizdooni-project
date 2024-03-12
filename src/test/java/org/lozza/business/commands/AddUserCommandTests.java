package org.lozza.business.commands;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.lozza.business.services.UserService;
import org.lozza.cli.command.requests.AddUserBody;
import org.lozza.cli.CLI;
import org.lozza.cli.command.Command;
import org.lozza.cli.command.requests.utils.AddressBody;

public class AddUserCommandTests {

    @AfterEach
    public void reset() {
        UserService.reset();
    }
    @Test
    public void addUserTest1() {
        Assertions.assertDoesNotThrow(() -> {
            final Command command = CLI.getCommandFromInput("addUser {\"role\": \"client\"" +
                    ", \"username\": \"user1\", " +
                    "\"password\": \"1234\", " +
                    "\"email\": \"user1@gmail.com\", " +
                    "\"address\": {" +
                    "   \"country\": \"Iran\", " +
                    "   \"city\": \"Tehran\", " +
                    "   \"street\":\"North Kargar\"" +
                    "}}");

            final AddUserBody body = (AddUserBody) command.body();
            Assertions.assertEquals(new AddUserBody(
                    "client",
                    "user1",
                    "1234",
                    "user1@gmail.com",
                    new AddressBody("Iran", "Tehran", "North Kargar")
            ), body);
        });
    }
}

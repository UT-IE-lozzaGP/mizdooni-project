package org.lozza.cli.command;

import org.lozza.business.services.UtilsService;
import org.lozza.cli.command.responses.Response;
import org.lozza.cli.command.requests.AddUserBody;
import org.lozza.business.services.UserService;

public record AddUserCommand(AddUserBody body) implements Command {

    @Override
    public Response run() {
        try {
            UserService.addUser(
                    body().role(),
                    body.username(),
                    body.password(),
                    body.email(),
                    UtilsService.getAddress(
                            body.address().country(),
                            body.address().city(),
                            body.address().street())
            );
            return new Response(true, "User added successfully.");
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "AddUserCommand{" +
                "body=" + body +
                '}';
    }
}

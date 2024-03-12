package org.lozza.cli.command;

import org.lozza.business.services.ReservationService;
import org.lozza.business.services.UserService;
import org.lozza.cli.command.requests.CancelReservationBody;
import org.lozza.cli.command.responses.Response;

public record CancelReservationCommand(CancelReservationBody body) implements Command {

    @Override
    public Response run() {
        try {
            ReservationService.cancelReservation(
                    UserService.getClientByForce(body.username()),
                    body.reservationNumber()
            );
            return new Response(true, "Reservation cancelled successfully.");
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "CancelReservationCommand{" +
                "body=" + body +
                '}';
    }
}

package org.lozza.cli.command;

import org.lozza.business.ds.Reservation;
import org.lozza.business.services.ReservationService;
import org.lozza.business.services.UserService;
import org.lozza.cli.command.requests.ShowReservationHistoryBody;
import org.lozza.cli.command.responses.tuples.ReservationHistoryTuple;
import org.lozza.cli.command.responses.Response;
import org.lozza.cli.command.responses.ShowReservationHistoryResponseBody;

import java.util.List;

public record ShowReservationHistoryCommand(ShowReservationHistoryBody body) implements Command{
    @Override
    public Response run() {
        try {
            List<Reservation> history = ReservationService.showReservationHistory(
                    UserService.getClientByForce(body.username())
            );
            List<ReservationHistoryTuple> historyData = history.stream()
                    .map(reservation -> new ReservationHistoryTuple(
                            reservation.reservationNumber(),
                            reservation.table().restaurant().name(),
                            reservation.table().tableNumber(),
                            reservation.dateTime()
                    ))
                    .toList();
            return new Response(true, new ShowReservationHistoryResponseBody(historyData));
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "ShowReservationHistoryCommand{" +
                "body=" + body +
                '}';
    }
}

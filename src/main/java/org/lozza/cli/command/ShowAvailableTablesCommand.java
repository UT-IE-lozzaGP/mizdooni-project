package org.lozza.cli.command;

import org.lozza.business.ds.Reservation;
import org.lozza.business.ds.Restaurant;
import org.lozza.business.ds.Table;
import org.lozza.business.services.ReservationService;
import org.lozza.business.services.RestaurantService;
import org.lozza.business.services.TableService;
import org.lozza.business.services.UtilsService;
import org.lozza.cli.command.requests.ShowAvailableTablesBody;
import org.lozza.cli.command.responses.Response;
import org.lozza.cli.command.responses.ShowAvailableTablesResponseBody;
import org.lozza.cli.command.responses.tuples.AvailableTableTuple;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record ShowAvailableTablesCommand(ShowAvailableTablesBody body) implements Command {
    @Override
    public Response run() {
        try {
            Restaurant restaurant = RestaurantService.getRestaurantByForce(body.restaurantName());
            List<Reservation> reservations = ReservationService.getAllReservationsByRestaurant(restaurant);
            List<Table> tables = TableService.getAllTablesByRestaurant(restaurant);
            List<LocalDateTime> slots = RestaurantService.getAllReservationsPossible(
                    restaurant,
                    LocalDate.now(),
                    LocalDate.now().plusDays(2)
            );

            List<AvailableTableTuple> availableTables = tables.stream()
                    .map(table -> new AvailableTableTuple(
                            table.tableNumber(),
                            table.seatsNumber(),
                            slots.stream()
                                    .filter(slot ->
                                            reservations.stream()
                                                    .filter(reservation -> table.equals(reservation.table()))
                                                    .noneMatch(reservation -> slot.equals(reservation.dateTime()))
                                    ).map(UtilsService::convertDateTime)
                                    .toList()
                    ))
                    .toList();
            return new Response(true, new ShowAvailableTablesResponseBody(availableTables));
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "ShowAvailableTablesCommand{" +
                "body=" + body +
                '}';
    }
}

package org.mizdooni.business.services;

import org.mizdooni.business.entry.Reservation;
import org.mizdooni.business.entry.Restaurant;
import org.mizdooni.business.entry.Table;
import org.mizdooni.business.collections.ReservationCollection;
import org.mizdooni.business.entry.user.Client;
import org.mizdooni.business.services.exceptions.ReservationServiceException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ReservationService {
    private static ReservationCollection reservations = new ReservationCollection();

    public static int reserveTable(
            Client client,
            Table table,
            LocalDateTime dateTime
    ) throws ReservationServiceException {
        checkClientIsValid(client);
        checkTableIsValid(table);
        checkDateTimeIsValid(dateTime);
        checkDateTimeHasNotPassed(dateTime);
        checkTimeSlotIsAvailable(table, dateTime);

        Reservation newReservation = new Reservation(client, table, dateTime);
        reservations.add(newReservation);
        return newReservation.reservationNumber();
    }

    protected static void checkTimeSlotIsAvailable(Table table, LocalDateTime dateTime) throws ReservationServiceException {
        if (reservations.stream()
                .anyMatch(reservation -> reservation.overlap(table, dateTime)))
            throw new ReservationServiceException(ReservationServiceException.Type.TimeSlotAlreadyBooked);
    }

    protected static void checkClientIsValid(Client client) throws ReservationServiceException {
        if (client == null)
            throw new ReservationServiceException(ReservationServiceException.Type.InvalidClient);
    }

    protected static void checkTableIsValid(Table table) throws ReservationServiceException {
        if (table == null)
            throw new ReservationServiceException(ReservationServiceException.Type.InvalidTable);
    }

    protected static void checkDateTimeHasNotPassed(LocalDateTime datetime) throws ReservationServiceException {
        LocalDateTime currentDateTime = LocalDateTime.now();
        if (datetime == null || datetime.isBefore(currentDateTime)) {
            throw new ReservationServiceException(ReservationServiceException.Type.PassedDateTime);
        }
    }

    protected static void checkDateTimeIsValid(LocalDateTime datetime) throws ReservationServiceException {
        if (datetime == null || datetime.getMinute() != 0) {
            throw new ReservationServiceException(ReservationServiceException.Type.InvalidDateTime);
        }
    }

    public static Optional<Reservation> getReservation(Client client, int reservationNumber) {
        return reservations.stream()
                .filter(reservation -> client.equals(reservation.client()) && reservationNumber == reservation.reservationNumber())
                .findFirst();
    }

    public static Reservation getReservationByForce(Client client, int reservationNumber) throws ReservationServiceException {
        return getReservation(client, reservationNumber)
                .orElseThrow(() -> new ReservationServiceException(ReservationServiceException.Type.ReservationNotFound));
    }

    public static void cancelReservation(Client client, int reservationNumber) throws ReservationServiceException {
        checkClientIsValid(client);
        Reservation reservation = getReservationByForce(client, reservationNumber);
        checkDateTimeHasNotPassed(reservation.dateTime());
        reservations.remove(reservation);
    }

    public static List<Reservation> getAllReservationsByRestaurant(Restaurant restaurant) {
        return reservations.stream()
                .filter(reservation -> restaurant.equals(reservation.table().restaurant()))
                .toList();
    }

    public static List<Reservation> getAllReservationsByClient(Client client) {
        return reservations.stream()
                .filter(reservation -> reservation.client().equals(client))
                .toList();
    }

    public static void reset() {
        reservations.clear();
    }

    protected static void setReservations(List<Reservation> reservations) {
        ReservationService.reservations = new ReservationCollection();
        ReservationService.reservations.addAll(reservations);
    }
}

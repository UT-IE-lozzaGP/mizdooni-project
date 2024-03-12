package org.lozza.services;

import org.junit.jupiter.api.*;
import org.lozza.business.entry.Reservation;
import org.lozza.business.services.*;
import org.lozza.business.services.exceptions.ReservationServiceException;
import org.lozza.business.services.exceptions.RestaurantServiceException;
import org.lozza.business.services.exceptions.TableServiceException;
import org.lozza.business.services.exceptions.UserServiceException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class ReservationServiceTests extends ReservationService {
    @BeforeAll
    public static void init() {
        UserService.reset();
        RestaurantService.reset();
        try {
            UserService.addUser(
                    "manager",
                    "user1",
                    "1234",
                    "user1@gmail.com",
                    UtilsService.getAddress("Iran", "Tehran")
            );
            UserService.addUser(
                    "client",
                    "user2",
                    "1234",
                    "user2@gmail.com",
                    UtilsService.getAddress("Iran", "Tehran")
            );
            RestaurantService.addRestaurant(
                    "restaurant1",
                    UserService.getManagerByForce("user1"),
                    "Thai",
                    "test restaurant",
                    UtilsService.getTime("10:00"),
                    UtilsService.getTime("23:00"),
                    UtilsService.getAddress("US", "Washington DC", "14th")
                    );
            TableService.addTable(
                    RestaurantService.getRestaurantByForce("restaurant1"),
                    UserService.getManagerByForce("user1"),
                    1,
                    4
            );
        } catch (Exception ignored) {
        }
    }

    @AfterAll
    public static void reset() {
        UserService.reset();
        RestaurantService.reset();
        TableService.reset();
    }

    @AfterEach
    public void clearReservations() {
        ReservationService.reset();
    }

    @Test
    public void isDateTimeValidTests() {
        Assertions.assertDoesNotThrow(() ->
                ReservationService.checkDateTimeIsValid(
                        LocalDateTime.of(2000, 4, 5, 10, 0)));
        Assertions.assertThrows(ReservationServiceException.class, () ->
                ReservationService.checkDateTimeIsValid(
                        LocalDateTime.of(2000, 4, 5, 10, 30)));
        Assertions.assertThrows(ReservationServiceException.class, () ->
                ReservationService.reserveTable(
                        UserService.getClientByForce("user2"),
                        TableService.getTableByForce(
                                RestaurantService.getRestaurantByForce("restaurant1"),
                                1
                        ),
                        LocalDateTime.of(2000, 4, 5, 10, 30)
                ));
        Assertions.assertThrows(ReservationServiceException.class, () ->
                ReservationService.reserveTable(
                        UserService.getClientByForce("user2"),
                        TableService.getTableByForce(
                                RestaurantService.getRestaurantByForce("restaurant1"),
                                1
                        ),
                        LocalDateTime.of(2020, 4, 5, 10, 0)
                ));
    }

    @Test
    public void cancelReservation()
            throws UserServiceException, RestaurantServiceException, TableServiceException {
        Reservation reservation = new Reservation(
                12,
                UserService.getClientByForce("user2"),
                TableService.getTableByForce(
                        RestaurantService.getRestaurantByForce("restaurant1"),
                        1
                ),
                LocalDateTime.of(2020, 5, 5, 12, 0)
        );
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation);
        ReservationService.setReservations(reservations);
        Assertions.assertThrows(ReservationServiceException.class, () ->
                ReservationService.cancelReservation(
                        UserService.getClientByForce("user2"),
                        12
                ));

    }
    @Test
    public void addReservationTests()
            throws UserServiceException, RestaurantServiceException, TableServiceException, ReservationServiceException {
        ReservationService.reserveTable(
                UserService.getClientByForce("user2"),
                TableService.getTableByForce(
                        RestaurantService.getRestaurantByForce("restaurant1"),
                        1
                ),
                LocalDateTime.of(2024, 4, 5, 10, 0)
        );
        ReservationService.reserveTable(
                UserService.getClientByForce("user2"),
                TableService.getTableByForce(
                        RestaurantService.getRestaurantByForce("restaurant1"),
                        1
                ),
                LocalDateTime.of(2024, 4, 5, 20, 0)
        );
        ReservationService.reserveTable(
                UserService.getClientByForce("user2"),
                TableService.getTableByForce(
                        RestaurantService.getRestaurantByForce("restaurant1"),
                        1
                ),
                LocalDateTime.of(2024, 4, 5, 22, 0)
        );
    }
}

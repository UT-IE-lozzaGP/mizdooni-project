package org.lozza.services;

import org.junit.jupiter.api.*;
import org.lozza.business.services.RestaurantService;
import org.lozza.business.services.TableService;
import org.lozza.business.services.UserService;
import org.lozza.business.services.UtilsService;
import org.lozza.business.services.exceptions.TableServiceException;

public class TableServiceTests extends TableService {

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
                    "5678",
                    "user2@gmail.com",
                    UtilsService.getAddress("Iran", "Tehran")
            );

            RestaurantService.addRestaurant(
                    "restaurant1",
                    UserService.getManager("user1").orElse(null),
                    "Thai",
                    "test thai food",
                    UtilsService.getTime("10:00"),
                    UtilsService.getTime("23:00"),
                    UtilsService.getAddress("Iran", "Tehran", "streetX")
            );
            RestaurantService.addRestaurant(
                    "restaurant2",
                    UserService.getManager("user1").orElse(null),
                    "Iranian",
                    "test iranian food",
                    UtilsService.getTime("05:00"),
                    UtilsService.getTime("22:00"),
                    UtilsService.getAddress("Iran", "Tehran", "streetZ")
            );
        } catch (Exception ignored) {
        }
    }

    @AfterAll
    public static void clearUsers() {
        UserService.reset();
    }

    @AfterAll
    public static void clearRestaurants() {
        RestaurantService.reset();
    }

    @AfterEach void clearTables() {
        TableService.reset();
    }

    @Test
    public void checkTableNumber() {
        try {
            TableService.addTable(
                    RestaurantService.getRestaurant("restaurant1").orElse(null),
                    UserService.getManager("user1").orElse(null),
                    1,
                    4
            );
            Assertions.assertThrows(TableServiceException.class, () ->
                    TableService.addTable(
                            RestaurantService.getRestaurant("restaurant1").orElse(null),
                            UserService.getManager("user1").orElse(null),
                            1,
                            5
                    ));
            try {
                TableService.addTable(
                        RestaurantService.getRestaurant("restaurant1").orElse(null),
                        UserService.getManager("user1").orElse(null),
                        1,
                        5
                );
            } catch (TableServiceException e) {
                Assertions.assertEquals(TableServiceException.Type.TableNumberNotNew, e.getType());
            }
        } catch (Exception ignored) {
        }
    }
    @Test
    public void checkSeatsNumber() {
        Assertions.assertDoesNotThrow(() -> TableService.checkSeatsNumberIsValid(4));
        Assertions.assertThrows(TableServiceException.class, () -> TableService.checkSeatsNumberIsValid(0));
        Assertions.assertThrows(TableServiceException.class, () -> TableService.checkSeatsNumberIsValid(-1));
    }

    @Test
    public void checkUniqueness() {
        try {
            TableService.addTable(
                    RestaurantService.getRestaurant("restaurant1").orElse(null),
                    UserService.getManager("user1").orElse(null),
                    1,
                    4
            );
            TableService.addTable(
                    RestaurantService.getRestaurant("restaurant2").orElse(null),
                    UserService.getManager("user1").orElse(null),
                    2,
                    2
            );
        } catch (Exception ignored) {
        }
        Assertions.assertDoesNotThrow(() -> TableService.checkTableNumberIsUnique(
                RestaurantService.getRestaurant("restaurant1").orElse(null),
                2));
        Assertions.assertThrows(TableServiceException.class, () -> TableService.checkTableNumberIsUnique(
                RestaurantService.getRestaurant("restaurant2").orElse(null),
                2));
    }
}

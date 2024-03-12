package org.lozza.services;

import org.junit.jupiter.api.*;
import org.lozza.business.entry.utils.Address;
import org.lozza.business.services.RestaurantService;
import org.lozza.business.services.UserService;
import org.lozza.business.services.UtilsService;
import org.lozza.business.services.exceptions.RestaurantServiceException;
import org.lozza.business.services.exceptions.UserServiceException;

import java.time.LocalTime;

public class RestaurantServiceTests extends RestaurantService {

    @BeforeAll
    public static void init() {
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
        } catch (Exception ignored) {
        }
    }

    @AfterAll
    public static void clearUsers() {
        UserService.reset();
    }

    @AfterEach
    public void clearRestaurants() {
        RestaurantService.reset();
    }

    @Test
    public void addRestaurant() {
        Assertions.assertDoesNotThrow(() -> RestaurantService.addRestaurant(
                "restaurant1",
                UserService.getManager("user1").orElse(null),
                "Thai",
                "test thai food",
                UtilsService.getTime("10:00"),
                UtilsService.getTime("22:00"),
                UtilsService.getAddress("Iran", "Tehran", "streetX")
        ));
    }
    @Test
    public void checkName() {
        try {
            RestaurantService.addRestaurant(
                    "restaurant1",
                    UserService.getManager("user1").orElse(null),
                    "Thai",
                    "test thai food",
                    UtilsService.getTime("10:00"),
                    UtilsService.getTime("22:00"),
                    UtilsService.getAddress("Iran", "Tehran", "streetX")
            );
            Assertions.assertThrows(RestaurantServiceException.class, () ->
                    RestaurantService.addRestaurant(
                            "restaurant1",
                            UserService.getManager("user1").orElse(null),
                            "Iranian",
                            "test iranian food",
                            UtilsService.getTime("08:00"),
                            UtilsService.getTime("20:00"),
                            UtilsService.getAddress("Iran", "Tehran", "streetY")
                    ));
            try {
                RestaurantService.addRestaurant(
                        "restaurant1",
                        UserService.getManager("user1").orElse(null),
                        "Iranian",
                        "test iranian food",
                        UtilsService.getTime("08:00"),
                        UtilsService.getTime("20:00"),
                        UtilsService.getAddress("Iran", "Tehran", "streetY")
                );
            } catch (RestaurantServiceException e) {
                Assertions.assertEquals(RestaurantServiceException.Type.NameNotNew, e.getType());
            }

        } catch (Exception ignored) {
        }
    }
    @Test
    public void isTimeValidTests() {
        Assertions.assertDoesNotThrow(() -> RestaurantService.checkTimeIsValid(UtilsService.getTime("23:00")));
        Assertions.assertDoesNotThrow(() -> RestaurantService.checkTimeIsValid(UtilsService.getTime("22:00")));
        Assertions.assertDoesNotThrow(() -> RestaurantService.checkTimeIsValid(UtilsService.getTime("08:00")));
        Assertions.assertThrows(RestaurantServiceException.class, () -> RestaurantService.checkTimeIsValid(UtilsService.getTime("08:30")));
    }
    @Test
    public void searchRestaurantTests() throws UserServiceException,RestaurantServiceException {
            RestaurantService.addRestaurant(
                    "Restaurant1",
                    UserService.getManagerByForce("user1"),
                    "iranian",
                    "hichi",
                    LocalTime.of(9, 0),
                    LocalTime.of(22, 0),
                    new Address("Country", "City", "Street A")
            );
            RestaurantService.addRestaurant(
                    "Restaurant2",
                    UserService.getManagerByForce("user1"),
                    "american",
                    "...",
                    LocalTime.of(11, 0),
                    LocalTime.of(23, 0),
                    UtilsService.getAddress("California", "Los Angeles", "Street B")
            );
        Assertions.assertEquals(1, RestaurantService.searchRestaurantByName("Restaurant1").size());
        Assertions.assertEquals(1, RestaurantService.searchRestaurantByName("Restaurant2").size());
        Assertions.assertEquals(0, RestaurantService.searchRestaurantByName("Non-existent Restaurant").size());

        Assertions.assertEquals(1, RestaurantService.searchRestaurantByType("iranian").size());
        Assertions.assertEquals(1, RestaurantService.searchRestaurantByType("american").size());
        Assertions.assertEquals(0, RestaurantService.searchRestaurantByType("Non-existent Type").size());
    }
}

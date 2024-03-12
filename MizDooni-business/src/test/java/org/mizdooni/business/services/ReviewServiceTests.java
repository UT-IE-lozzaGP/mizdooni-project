package org.mizdooni.business.services;

import org.junit.jupiter.api.*;
import org.mizdooni.business.services.exceptions.ReviewServiceException;

public class ReviewServiceTests extends ReviewService {
    @BeforeAll
    public static void init() {
        UserService.reset();
        RestaurantService.reset();
        try {
            UserService.addUser(
                    "manager",
                    "manager1",
                    "1234",
                    "user1@gmail.com",
                    UtilsService.getAddress("Iran", "Tehran")
            );
            UserService.addUser(
                    "client",
                    "client1",
                    "5678",
                    "user2@gmail.com",
                    UtilsService.getAddress("Iran", "Tehran")
            );

            RestaurantService.addRestaurant(
                    "restaurant1",
                    UserService.getManager("manager1").orElse(null),
                    "Thai",
                    "test thai food",
                    UtilsService.getTime("10:00"),
                    UtilsService.getTime("23:00"),
                    UtilsService.getAddress("Iran", "Tehran", "streetX")
            );
            RestaurantService.addRestaurant(
                    "restaurant2",
                    UserService.getManager("manager1").orElse(null),
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

    @AfterEach
    void clearTables() {
        ReviewService.reset();
    }

    @Test
    public void rateTests() {
        Assertions.assertThrows(ReviewServiceException.class, () ->
                ReviewService.checkRate(5.1F));
        Assertions.assertThrows(ReviewServiceException.class, () ->
                ReviewService.checkRate(-2.1F));
        Assertions.assertDoesNotThrow(() ->
                ReviewService.checkRate(1.5F));
    }

    @Test
    public void addReviewTests() {
        Assertions.assertDoesNotThrow(() ->
                ReviewService.addOrUpdateReview(
                        UserService.getClientByForce("client1"),
                        RestaurantService.getRestaurantByForce("restaurant1"),
                        4.5F,
                        5F,
                        3.5F,
                        2.6F,
                        "nothing!"
                ));
    }
}

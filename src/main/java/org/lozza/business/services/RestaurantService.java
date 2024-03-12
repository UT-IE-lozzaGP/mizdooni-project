package org.lozza.business.services;


import org.lozza.business.entry.Restaurant;
import org.lozza.business.collections.RestaurantCollection;
import org.lozza.business.entry.user.Manager;
import org.lozza.business.entry.utils.Address;
import org.lozza.business.services.exceptions.RestaurantServiceException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RestaurantService {
    private static final RestaurantCollection restaurants = new RestaurantCollection();

    public static void addRestaurant(
            String name,
            Manager manager,
            String type,
            String description,
            LocalTime startTime,
            LocalTime endTime,
            Address address) throws RestaurantServiceException {

        checkManagerIsValid(manager);
        checkNameIsUnique(name);
        checkTimeIsValid(startTime);
        checkTimeIsValid(endTime);
        checkAddressIsValid(address);
        restaurants.add(new Restaurant(
                name,
                manager,
                type,
                description,
                startTime,
                endTime,
                address
                ));
    }

    public static Restaurant getRestaurantById(String id) throws RestaurantServiceException {
        return restaurants.stream()
                .filter(restaurant -> restaurant.id().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new RestaurantServiceException(RestaurantServiceException.Type.RestaurantNotFound));
    }
    public static List<Restaurant> searchRestaurantByManager(Manager manager) {
        return restaurants.stream()
                .filter(restaurant -> manager.equals(restaurant.manager()))
                .toList();
    }

    public static List<Restaurant> searchRestaurantByName(String name) {
        return restaurants.stream()
                .filter(restaurant -> name.equals(restaurant.name()))
                .toList();
    }

    public static List<Restaurant> searchRestaurantByType(String type) {
        return restaurants.stream()
                .filter(restaurant -> type.equals(restaurant.type()))
                .toList();
    }

    public static Optional<Restaurant> getRestaurant(String name) {
        return restaurants.stream()
                .filter(table -> name.equals(table.name()))
                .findFirst();
    }
    public static Restaurant getRestaurantByForce(String name) throws RestaurantServiceException {
        return getRestaurant(name)
                .orElseThrow(() -> new RestaurantServiceException(RestaurantServiceException.Type.RestaurantNotFound));
    }

    public static List<LocalDateTime> getAllReservationsPossible(Restaurant restaurant, LocalDate from, LocalDate to) {
        LocalTime start = restaurant.startTime();
        LocalTime end = restaurant.endTime();
        LocalDateTime curr = LocalDateTime.of(from, start);
        List<LocalDateTime> dateTimes = new ArrayList<>();
        while(curr.isBefore(LocalDateTime.of(to, end))) {
            dateTimes.add(curr);
            curr = curr.plusHours(1);
            if (curr.toLocalTime().equals(end)) {
                curr = LocalDateTime.of(
                        curr.toLocalDate().plusDays(1),
                        start);
            }
        }
        return dateTimes;
    }

    public static List<Restaurant> getAllRestaurants() {
        return restaurants;
    }

    protected static void checkManagerIsValid(Manager manager) throws RestaurantServiceException {
        if (manager == null)
            throw new RestaurantServiceException(RestaurantServiceException.Type.InvalidManager);
    }
    protected static void checkNameIsUnique(String name) throws RestaurantServiceException {
        if (name == null || name.isEmpty()
                || restaurants.stream().anyMatch(restaurant -> name.equals(restaurant.name())))
            throw new RestaurantServiceException(RestaurantServiceException.Type.NameNotNew);
    }

    protected static void checkTimeIsValid(LocalTime time) throws RestaurantServiceException {
        if (time == null || time.getMinute() != 0)
            throw new RestaurantServiceException(RestaurantServiceException.Type.InvalidTime);
    }

    protected static void checkAddressIsValid(Address address) throws RestaurantServiceException {
        if (address == null
                || address.country() == null || address.country().isEmpty()
                || address.city() == null || address.city().isEmpty()
                || address.street() == null || address.street().isEmpty())
            throw new RestaurantServiceException(RestaurantServiceException.Type.InvalidAddress);
    }

    public static void reset() {
        restaurants.clear();
    }
}

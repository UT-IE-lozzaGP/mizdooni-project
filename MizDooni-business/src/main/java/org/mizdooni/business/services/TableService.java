package org.mizdooni.business.services;

import org.mizdooni.business.entry.Restaurant;
import org.mizdooni.business.entry.Table;
import org.mizdooni.business.collections.TableCollection;
import org.mizdooni.business.entry.user.Manager;
import org.mizdooni.business.services.exceptions.TableServiceException;

import java.util.List;
import java.util.Optional;

public class TableService {
    private static final TableCollection tables = new TableCollection();


    public static void addTable(
            Restaurant restaurant,
            Manager manager,
            int tableNumber,
            int seatsNumber
    ) throws TableServiceException {
        checkManagerIsValid(manager);
        checkRestaurantIsValid(restaurant);
        checkTableNumberIsUnique(restaurant, tableNumber);
        checkSeatsNumberIsValid(seatsNumber);
        tables.add(new Table(
                restaurant,
                tableNumber,
                seatsNumber
        ));
    }

    public static Optional<Table> getTable(Restaurant restaurant, int tableNumber) {
        return tables.stream()
                .filter(table -> restaurant.equals(table.restaurant()) && tableNumber == table.tableNumber())
                .findFirst();
    }
    public static Table getTableByForce(Restaurant restaurant, int tableNumber) throws TableServiceException {
        return getTable(restaurant, tableNumber)
                .orElseThrow(() -> new TableServiceException(TableServiceException.Type.TableNotFound));
    }

    public static List<Table> getAllTablesByRestaurant(Restaurant restaurant) {
        return tables.stream()
                .filter(table -> restaurant.equals(table.restaurant()))
                .toList();
    }

    protected static void checkManagerIsValid(Manager manager) throws TableServiceException {
        if (manager == null)
            throw new TableServiceException(TableServiceException.Type.InvalidManager);
    }
    protected static void checkRestaurantIsValid(Restaurant restaurant) throws TableServiceException {
        if (restaurant == null)
            throw new TableServiceException(TableServiceException.Type.InvalidRestaurant);
    }
    protected static void checkTableNumberIsUnique(Restaurant restaurant, int tableNumber) throws TableServiceException {
        if (restaurant == null ||
                tables.stream()
                        .anyMatch(table -> restaurant.equals(table.restaurant()) && tableNumber == table.tableNumber()))
            throw new TableServiceException(TableServiceException.Type.TableNumberNotNew);
    }

    protected static void checkSeatsNumberIsValid(Integer seatsNumber) throws TableServiceException {
        if (seatsNumber == null || seatsNumber <= 0)
            throw new TableServiceException(TableServiceException.Type.InvalidSeatsNumber);
    }

    public static void reset() {
        tables.clear();
    }
}

package org.lozza.business.entry;

import java.util.Objects;

public record Table(
        Restaurant restaurant,
        int tableNumber,
        int seatsNumber
) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table table = (Table) o;
        return tableNumber == table.tableNumber && seatsNumber == table.seatsNumber && Objects.equals(restaurant, table.restaurant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(restaurant, tableNumber, seatsNumber);
    }
}

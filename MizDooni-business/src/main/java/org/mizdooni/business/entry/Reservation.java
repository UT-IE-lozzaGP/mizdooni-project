package org.mizdooni.business.entry;

import org.mizdooni.business.entry.user.Client;

import java.time.LocalDateTime;
import java.util.Objects;

public record Reservation(
        int reservationNumber,
        Client client,
        Table table,
        LocalDateTime dateTime) {

    public Reservation(Client client, Table table, LocalDateTime dateTime) {
        this(Objects.hash(table, dateTime), client, table, dateTime);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(client, that.client) && Objects.equals(table, that.table) && Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client, table, dateTime);
    }

    public boolean overlap(Table table, LocalDateTime dateTime) {
        return table.equals(table()) && dateTime.equals(dateTime());
    }
}

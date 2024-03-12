package org.lozza.business.entry;

import org.lozza.business.entry.user.Manager;
import org.lozza.business.entry.utils.Address;

import java.time.LocalTime;
import java.util.Objects;

public record Restaurant(
        String id,
        String name,
        Manager manager,
        String type,
        String description,
        LocalTime startTime,
        LocalTime endTime,
        Address address) {

    public Restaurant(
            String name,
            Manager manager,
            String type,
            String description,
            LocalTime startTime,
            LocalTime endTime,
            Address address) {
        this(
                Integer.toUnsignedString(Objects.hash(name, manager, type, description, startTime, endTime, address)),
                name,
                manager,
                type,
                description,
                startTime,
                endTime,
                address);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(name, that.name) && Objects.equals(manager, that.manager) && Objects.equals(type, that.type) && Objects.equals(description, that.description) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Integer.parseUnsignedInt(id);
    }
}

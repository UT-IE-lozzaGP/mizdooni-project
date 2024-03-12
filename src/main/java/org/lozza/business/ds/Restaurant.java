package org.lozza.business.ds;

import org.lozza.business.ds.user.Manager;
import org.lozza.business.ds.utils.Address;

import java.time.LocalTime;
import java.util.Objects;

public record Restaurant(
        String name,
        Manager manager,
        String type,
        String description,
        LocalTime startTime,
        LocalTime endTime,
        Address address) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(name, that.name) && Objects.equals(manager, that.manager) && Objects.equals(type, that.type) && Objects.equals(description, that.description) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, manager, type, description, startTime, endTime, address);
    }
}

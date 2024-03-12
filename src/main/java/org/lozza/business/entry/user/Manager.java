package org.lozza.business.entry.user;

import org.lozza.business.entry.utils.Address;

import java.util.Objects;

public record Manager(
        String username,
        String password,
        String email,
        Address address
) implements User {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manager manager = (Manager) o;
        return Objects.equals(username, manager.username) && Objects.equals(password, manager.password) && Objects.equals(email, manager.email) && Objects.equals(address, manager.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, email, address);
    }
}

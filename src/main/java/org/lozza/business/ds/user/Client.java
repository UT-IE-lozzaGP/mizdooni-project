package org.lozza.business.ds.user;

import org.lozza.business.ds.utils.Address;

import java.util.Objects;

public record Client(
        String username,
        String password,
        String email,
        Address address
) implements User {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(username, client.username) && Objects.equals(password, client.password) && Objects.equals(email, client.email) && Objects.equals(address, client.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, email, address);
    }
}

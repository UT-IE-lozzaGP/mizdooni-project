package org.lozza.cli.command.requests;

import org.lozza.cli.command.requests.utils.AddressBody;

public record AddUserBody(
        String role,
        String username,
        String password,
        String email,
        AddressBody address) {
}

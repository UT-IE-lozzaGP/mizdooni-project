package org.lozza.business.entry.user;

import org.lozza.business.entry.utils.Address;

public interface User {
    String username();
    String email();
    String password();
    Address address();
}

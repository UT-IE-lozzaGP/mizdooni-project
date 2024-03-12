package org.mizdooni.business.entry.user;

import org.mizdooni.business.entry.utils.Address;

public interface User {
    String username();
    String email();
    String password();
    Address address();
}

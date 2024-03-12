package org.lozza.business.ds.user;

import org.lozza.business.ds.utils.Address;

public interface User {
    String username();
    String email();
    String password();
    Address address();
}

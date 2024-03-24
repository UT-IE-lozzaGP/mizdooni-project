package org.mizdooni.business.entity.user;

import lombok.NonNull;
import org.mizdooni.business.datatype.Address;
import org.mizdooni.business.datatype.Email;
import org.mizdooni.business.datatype.Password;
import org.mizdooni.business.datatype.Username;

public class Customer extends User {

    public Customer(@NonNull Username username, @NonNull Password password, @NonNull Email email, @NonNull Address address) {
        super(username, password, email, address);
    }
}

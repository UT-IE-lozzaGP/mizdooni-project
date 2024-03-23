package org.mizdooni.business.entity.user;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.mizdooni.business.datatype.Address;
import org.mizdooni.business.datatype.Email;
import org.mizdooni.business.datatype.Password;
import org.mizdooni.business.datatype.Username;

@EqualsAndHashCode(callSuper = true)
public class Client extends User {

    public Client(@NonNull Username username, @NonNull Password password, @NonNull Email email, @NonNull Address address) {
        super(username, password, email, address);
    }
}

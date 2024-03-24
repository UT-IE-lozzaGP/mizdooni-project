package org.mizdooni.business.entity.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.mizdooni.business.datatype.Address;
import org.mizdooni.business.datatype.id.user.CustomerID;
import org.mizdooni.business.datatype.string.Email;
import org.mizdooni.business.datatype.string.Password;
import org.mizdooni.business.datatype.string.Username;

@Getter
public class Customer extends User {
    @NonNull
    private final CustomerID id = new CustomerID();

    @Builder
    public Customer(@NonNull Username username, @NonNull Password password, @NonNull Email email, @NonNull Address address) {
        super(username, password, email, address);
    }
}

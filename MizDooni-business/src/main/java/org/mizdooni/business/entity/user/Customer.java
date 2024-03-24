package org.mizdooni.business.entity.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.mizdooni.business.datatype.Address;
import org.mizdooni.business.datatype.Email;
import org.mizdooni.business.datatype.Password;
import org.mizdooni.business.datatype.Username;
import org.mizdooni.business.datatype.id.CustomerID;

@Getter
@Builder
public class Customer extends User {
    @NonNull
    @Builder.Default
    private final CustomerID id = new CustomerID();
    public Customer(@NonNull Username username, @NonNull Password password, @NonNull Email email, @NonNull Address address) {
        super(username, password, email, address);
    }
}

package org.mizdooni.business.entity.user;

import lombok.*;
import org.mizdooni.business.datatype.Email;
import org.mizdooni.business.datatype.ID;
import org.mizdooni.business.datatype.Password;
import org.mizdooni.business.datatype.Username;
import org.mizdooni.business.datatype.Address;

@Getter
@EqualsAndHashCode
abstract class User {
    @NonNull
    private ID id;
    @NonNull @Setter
    private Username username;
    @NonNull @Setter
    private Password password;
    @NonNull @Setter
    private Email email;
    @NonNull @Setter
    private Address address;

    protected User(@NonNull Username username, @NonNull Password password, @NonNull Email email, @NonNull Address address) {
        this.id = new ID();
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
    }
}

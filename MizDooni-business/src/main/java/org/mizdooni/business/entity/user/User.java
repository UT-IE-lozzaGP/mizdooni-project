package org.mizdooni.business.entity.user;

import lombok.*;
import org.mizdooni.business.datatype.Email;
import org.mizdooni.business.datatype.ID;
import org.mizdooni.business.datatype.Password;
import org.mizdooni.business.datatype.Username;
import org.mizdooni.business.datatype.Address;

@Getter
@EqualsAndHashCode
@Builder
abstract class User {
    @NonNull
    @Builder.Default
    private final ID id = new ID();
    @NonNull @Setter
    private Username username;
    @NonNull @Setter
    private Password password;
    @NonNull @Setter
    private Email email;
    @NonNull @Setter
    private Address address;

    protected User(@NonNull Username username, @NonNull Password password, @NonNull Email email, @NonNull Address address) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
    }
}

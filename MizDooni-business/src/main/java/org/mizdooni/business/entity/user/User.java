package org.mizdooni.business.entity.user;

import lombok.*;
import org.mizdooni.business.datatype.*;
import org.mizdooni.business.datatype.id.UserID;
import org.mizdooni.business.datatype.string.Email;
import org.mizdooni.business.datatype.string.Password;
import org.mizdooni.business.datatype.string.Username;

@Getter
@Builder
abstract class User {
    @NonNull
    @Builder.Default
    private final UserID id = new UserID();

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

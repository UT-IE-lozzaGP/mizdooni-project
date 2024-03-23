package org.mizdooni.business.entity.user;

import lombok.*;
import org.mizdooni.business.datatype.Email;
import org.mizdooni.business.datatype.ID;
import org.mizdooni.business.datatype.Password;
import org.mizdooni.business.datatype.Username;
import org.mizdooni.business.datatype.Address;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class User {
    @NonNull
    @Builder.Default
    private ID id = new ID();
    @NonNull
    private Username username;
    @NonNull
    private Password password;
    @NonNull
    private Email email;
    @NonNull
    private Address address;

    protected User(@NonNull Username username, @NonNull Password password, @NonNull Email email, @NonNull Address address) {
        this(new ID(), username, password, email, address);
    }
}

package org.mizdooni.business.entity.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.mizdooni.business.datatype.Address;
import org.mizdooni.business.datatype.string.Email;
import org.mizdooni.business.datatype.string.Password;
import org.mizdooni.business.datatype.string.Username;
import org.mizdooni.business.datatype.id.user.ManagerID;

@Getter
public class Manager extends User {
    @NonNull
    private final ManagerID id = new ManagerID();

    @Builder
    public Manager(@NonNull Username username, @NonNull Password password, @NonNull Email email, @NonNull Address address) {
        super(username, password, email, address);
    }
}

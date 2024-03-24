package org.mizdooni.business.entity.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.mizdooni.business.datatype.Address;
import org.mizdooni.business.datatype.Email;
import org.mizdooni.business.datatype.Password;
import org.mizdooni.business.datatype.Username;
import org.mizdooni.business.datatype.id.ManagerID;

@Getter
@Builder
public class Manager extends User {
    @NonNull
    @Builder.Default
    private final ManagerID id = new ManagerID();
    public Manager(@NonNull Username username, @NonNull Password password, @NonNull Email email, @NonNull Address address) {
        super(username, password, email, address);
    }
}

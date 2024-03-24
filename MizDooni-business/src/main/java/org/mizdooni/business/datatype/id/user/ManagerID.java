package org.mizdooni.business.datatype.id.user;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.mizdooni.business.datatype.id.ID;
import org.mizdooni.business.datatype.id.UserID;

@EqualsAndHashCode(callSuper = true)
@Data
public class ManagerID extends UserID {
}

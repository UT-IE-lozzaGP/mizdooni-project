package org.mizdooni.business.datatype.string;

import lombok.*;

@Data
public class Password {
    @NonNull
    private final String value;
}

package org.mizdooni.business.datatype.string;

import lombok.*;

@Data
public class Email {
    @NonNull
    private final String value;
}

package org.mizdooni.business.datatype.string;

import lombok.*;

@Data
public class Message {
    @NonNull
    private final String value;
}

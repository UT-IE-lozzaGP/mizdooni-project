package org.mizdooni.business.datatype.string;

import lombok.NonNull;
import lombok.Value;

@Value
public class Comment {
    @NonNull String value;
}

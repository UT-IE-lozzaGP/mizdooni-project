package org.mizdooni.business.datatype.string;

import lombok.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Value
public class Username {
    @NonNull String value;

    final private static Pattern pattern = Pattern.compile("^[a-zA-Z0-9_]{5,20}$");

    public Username(@NonNull String value) {
        verify(value);
        this.value = value;
    }
    private static void verify(@NonNull String value) {
        final Matcher matcher = pattern.matcher(value);
        if (!matcher.matches())
            throw new RuntimeException("username is not valid");
    }
}

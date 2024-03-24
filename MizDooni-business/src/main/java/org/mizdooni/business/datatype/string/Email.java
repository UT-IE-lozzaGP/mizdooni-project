package org.mizdooni.business.datatype.string;

import lombok.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Value
public class Email {
    @NonNull String value;

    final private static Pattern pattern = Pattern.compile("^[a-zA-Z0-9_]+@[a-zA-Z0-9_]+(.[a-zA-Z0-9_]+)+$");

    public Email(@NonNull String value) {
        verify(value);
        this.value = value;
    }
    private static void verify(@NonNull String value) {
        final Matcher matcher = pattern.matcher(value);
        if (!matcher.matches())
            throw new RuntimeException("email is not valid");
    }
}

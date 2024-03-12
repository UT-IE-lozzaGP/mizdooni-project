package org.lozza.json;

import com.google.gson.Gson;

public class JSON {

    public static Object extractJSON(String input, Class<?> clazz) {
        final Gson gson = new Gson();
        return gson.fromJson(input, clazz);
    }

    public static String serializeJSON(Object object) {
        final Gson gson = new Gson();
        return gson.toJson(object);
    }
}

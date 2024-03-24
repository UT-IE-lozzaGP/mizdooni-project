package org.mizdooni.business.datatype.id;

import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import java.util.concurrent.atomic.AtomicInteger;

@SuperBuilder
abstract class ID {
    @NonNull
    private final String value;

    private final AtomicInteger generator = new AtomicInteger(0);

    public ID() {
        value = Integer.toUnsignedString(generator.incrementAndGet());
    }
}

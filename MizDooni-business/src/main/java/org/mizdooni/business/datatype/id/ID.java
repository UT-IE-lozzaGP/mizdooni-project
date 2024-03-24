package org.mizdooni.business.datatype.id;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import java.util.concurrent.atomic.AtomicInteger;

@SuperBuilder
@NoArgsConstructor
abstract class ID {
    @NonNull
    private final String value = Integer.toUnsignedString(
            generator.incrementAndGet());

    private static final AtomicInteger generator = new AtomicInteger(0);
}

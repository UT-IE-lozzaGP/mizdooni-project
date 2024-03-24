package org.mizdooni.business.datatype;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.util.concurrent.atomic.AtomicInteger;

@EqualsAndHashCode
@Getter
@Builder
public class ID {
    @NonNull
    private final String value;

    private final AtomicInteger generator = new AtomicInteger(0);

    public ID() {
        value = Integer.toUnsignedString(generator.incrementAndGet());
    }
}

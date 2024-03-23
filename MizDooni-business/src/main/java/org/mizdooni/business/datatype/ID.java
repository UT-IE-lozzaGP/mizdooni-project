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
    private String id;

    private AtomicInteger idGenerator = new AtomicInteger(0);

    public ID() {
        id = Integer.toUnsignedString(idGenerator.incrementAndGet());
    }
}

package org.mizdooni.business.datatype.rating;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
abstract class Rating {
    @NonNull
    protected final Float value;
}

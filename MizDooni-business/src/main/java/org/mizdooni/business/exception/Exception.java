package org.mizdooni.business.exception;

import lombok.*;
import org.mizdooni.business.datatype.Message;
import java.util.List;

@Getter
@Builder
public class Exception extends java.lang.Exception {
    private final List<Message> messages;

    public Exception(@NonNull List<Message> messages) {
        this.messages = messages;
    }

}

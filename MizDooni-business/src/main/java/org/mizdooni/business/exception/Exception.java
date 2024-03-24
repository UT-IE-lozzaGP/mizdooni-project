package org.mizdooni.business.exception;

import lombok.*;
import org.mizdooni.business.datatype.string.Message;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class Exception extends java.lang.RuntimeException {
    private final List<Message> messages;

    public Exception(@NonNull List<Message> messages) {
        this.messages = messages;
    }

    public Exception() {
        this.messages = new ArrayList<>();
    }

    public void addMessage(@NonNull Message newMessage) {
        messages.add(newMessage);
    }
}

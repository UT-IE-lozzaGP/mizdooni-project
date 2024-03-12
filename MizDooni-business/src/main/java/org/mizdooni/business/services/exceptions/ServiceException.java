package org.mizdooni.business.services.exceptions;

abstract class ServiceException extends Exception{

    public interface Type {
        String getMessage();
    }

    private final Type type;
    public ServiceException(Type type) {
        this.type = type;
    }

    @Override
    public String getMessage() {
        return type.getMessage();
    }

    public Type getType() {
        return type;
    }
}

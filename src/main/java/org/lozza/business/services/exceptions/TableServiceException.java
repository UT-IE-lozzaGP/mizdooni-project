package org.lozza.business.services.exceptions;

public class TableServiceException extends ServiceException {
    public enum Type implements ServiceException.Type {
        InvalidManager("invalid manager"),
        InvalidRestaurant("invalid restaurant"),
        TableNumberNotNew("table name has been used before"),
        InvalidSeatsNumber("invalid seats number"),
        TableNotFound("table not found");

        private final String message;

        Type(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public TableServiceException(ServiceException.Type type) {
        super(type);
    }
}

package org.lozza.business.services.exceptions;

public class RestaurantServiceException extends ServiceException {
    public enum Type implements ServiceException.Type {
        InvalidTime("time is invalid"),
        InvalidManager("invalid manager"),
        InvalidName("name is invalid"),
        InvalidAddress("address is invalid"),
        NameNotNew("name was used before"),
        RestaurantNotFound("restaurant not found");

        private final String message;

        Type(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public RestaurantServiceException(ServiceException.Type type) {
        super(type);
    }
}

package org.lozza.business.services.exceptions;

public class ReviewServiceException extends ServiceException{
    public enum Type implements ServiceException.Type {
        InvalidClient("invalid client"),
        InvalidRestaurant("invalid restaurant"),
        InvalidRate("rate is invalid");

        private final String message;

        Type(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public ReviewServiceException(ReviewServiceException.Type type) {
        super(type);
    }
}

package org.lozza.business.services.exceptions;

public class UserServiceException extends ServiceException {

    public enum Type implements ServiceException.Type {
        EmailNotNew("email has been used before"),
        InvalidAddress("address is invalid"),
        InvalidEmail("email is invalid"),
        InvalidRole("user's role is invalid"),
        InvalidUsername("username is invalid"),
        UsernameNotNew("username has been used before"),
        UserNotFound("user not found"),
        WrongPassword("entered password is wrong");

        private final String message;

        Type(String message) {
            this.message = message;
        }
        public String getMessage() {
            return message;
        }
    }

    public UserServiceException(ServiceException.Type type) {
        super(type);
    }
}

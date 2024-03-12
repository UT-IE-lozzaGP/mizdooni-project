package org.mizdooni.business.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mizdooni.business.services.exceptions.UserServiceException;

public class UserServiceTests extends UserService {
    @Test
    public void validateUsername() {
        Assertions.assertThrows(UserServiceException.class, () -> checkUsernameIsValid(null));
        Assertions.assertDoesNotThrow(() -> checkUsernameIsValid("ali_dslhf"));
        Assertions.assertThrows(UserServiceException.class, () -> checkUsernameIsValid("sfhgfl:sad"));
        Assertions.assertDoesNotThrow(() -> checkUsernameIsValid("99734saf"));
        Assertions.assertThrows(UserServiceException.class, () -> checkUsernameIsValid("angry@you"));
    }

    @Test
    public void validateEmail() {
        Assertions.assertThrows(UserServiceException.class, () -> checkEmailIsValid(null));
        Assertions.assertThrows(UserServiceException.class, () -> checkEmailIsValid("ali_dslhf@"));
        Assertions.assertThrows(UserServiceException.class, () -> checkEmailIsValid("sfhgfl:sad.somethin"));
        Assertions.assertThrows(UserServiceException.class, () -> checkEmailIsValid("99734saf@."));
        Assertions.assertThrows(UserServiceException.class, () -> checkEmailIsValid("99734saf@.cc."));
        Assertions.assertDoesNotThrow(() -> checkEmailIsValid("99734saf@ut.cc87.sd"));
        Assertions.assertDoesNotThrow(() -> checkEmailIsValid("angry@you.com"));
    }

    @Test
    public void invalidRoleTest() {
        Assertions.assertThrows(UserServiceException.class, () -> addUser(
                "admin",
                "user1",
               "1234",
                "user1@gmail.com",
                UtilsService.getAddress(
                        "Iran",
                        "Tehran")));
    }

    @Test
    public void nullUsernameTest() {
        Assertions.assertThrows(UserServiceException.class, () -> addUser(
                "client",
                null,
                "1234",
                "user1@gmail.com",
                UtilsService.getAddress(
                        "Iran",
                        "Tehran")));
    }
}

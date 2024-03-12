package org.lozza.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.lozza.business.services.UserService;
import org.lozza.business.services.UtilsService;
import org.lozza.business.services.exceptions.UserServiceException;

public class UserServiceTests extends UserService {
    @Test
    public void validateUsername() {
        Assertions.assertThrows(UserServiceException.class, () -> UserService.checkUsernameIsValid(null));
        Assertions.assertDoesNotThrow(() -> UserService.checkUsernameIsValid("ali_dslhf"));
        Assertions.assertThrows(UserServiceException.class, () -> UserService.checkUsernameIsValid("sfhgfl:sad"));
        Assertions.assertDoesNotThrow(() -> UserService.checkUsernameIsValid("99734saf"));
        Assertions.assertThrows(UserServiceException.class, () -> UserService.checkUsernameIsValid("angry@you"));
    }

    @Test
    public void validateEmail() {
        Assertions.assertThrows(UserServiceException.class, () -> UserService.checkEmailIsValid(null));
        Assertions.assertThrows(UserServiceException.class, () -> UserService.checkEmailIsValid("ali_dslhf@"));
        Assertions.assertThrows(UserServiceException.class, () -> UserService.checkEmailIsValid("sfhgfl:sad.somethin"));
        Assertions.assertThrows(UserServiceException.class, () -> UserService.checkEmailIsValid("99734saf@."));
        Assertions.assertThrows(UserServiceException.class, () -> UserService.checkEmailIsValid("99734saf@.cc."));
        Assertions.assertDoesNotThrow(() -> UserService.checkEmailIsValid("99734saf@ut.cc87.sd"));
        Assertions.assertDoesNotThrow(() -> UserService.checkEmailIsValid("angry@you.com"));
    }

    @Test
    public void invalidRoleTest() {
        Assertions.assertThrows(UserServiceException.class, () -> UserService.addUser(
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
        Assertions.assertThrows(UserServiceException.class, () -> UserService.addUser(
                "client",
                null,
                "1234",
                "user1@gmail.com",
                UtilsService.getAddress(
                        "Iran",
                        "Tehran")));
    }
}

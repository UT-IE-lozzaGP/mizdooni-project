package org.lozza.business.services;

import org.lozza.business.ds.collections.UserCollection;
import org.lozza.business.ds.user.Client;
import org.lozza.business.ds.user.Manager;
import org.lozza.business.ds.user.User;
import org.lozza.business.ds.utils.Address;
import org.lozza.business.services.exceptions.UserServiceException;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {

    final private static Pattern usernamePattern = Pattern.compile("^[a-zA-Z0-9_]{5,20}$");
    final private static Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_]+@[a-zA-Z0-9_]+(.[a-zA-Z0-9_]+)+$");
    final private static UserCollection users = new UserCollection();

    public static void addUser(String role,
                               String username,
                               String password,
                               String email,
                               Address address) throws UserServiceException {
        checkRoleIsValid(role);
        checkUsernameIsValid(username);
        checkEmailIsValid(email);
        checkUsernameIsUnique(username);
        checkEmailIsUnique(email);
        checkAddressIsValid(address);

        users.add(switch (role) {
            case "client" -> new Client(username, password, email, address);
            case "manager" -> new Manager(username, password, email, address);
            default -> null;
        });
    }

    protected static void checkRoleIsValid(String role) throws UserServiceException {
        if (role == null || (!role.equals("client") && !role.equals("manager")))
            throw new UserServiceException(UserServiceException.Type.InvalidRole);
    }

    protected static void checkUsernameIsUnique(String username) throws UserServiceException {
        if (username == null || users.stream()
                    .anyMatch(user -> username.equals(user.username())))
            throw new UserServiceException(UserServiceException.Type.UsernameNotNew);
    }

    protected static void checkEmailIsUnique(String email) throws UserServiceException {
        if (email == null || users.stream()
                .anyMatch(user -> email.equals(user.email())))
            throw new UserServiceException(UserServiceException.Type.EmailNotNew);
    }

    protected static void checkUsernameIsValid(String username) throws UserServiceException {
        try {
            if (username == null) throw new Exception();
            final Matcher matcher = usernamePattern.matcher(username);
            if (!matcher.matches())
                throw new Exception();
        } catch (Exception e) {
            throw new UserServiceException(UserServiceException.Type.InvalidUsername);
        }
    }

    protected static void checkEmailIsValid(String email) throws UserServiceException {
        try {
            if (email == null) throw new Exception();
            final Matcher matcher = emailPattern.matcher(email);
            if (!matcher.matches())
                throw new Exception();
        } catch (Exception e) {
            throw new UserServiceException(UserServiceException.Type.InvalidEmail);
        }
    }

    public static Optional<User> getUser(String username) {
        return users.stream()
                .filter(user -> username.equals(user.username()))
                .findFirst();
    }
    public static Optional<Manager> getManager(String username) {
        return users.stream()
                .filter(user -> username.equals(user.username()) && user.getClass().equals(Manager.class))
                .map(user -> (Manager) user)
                .findFirst();
    }
    public static Optional<Client> getClient(String username) {
        return users.stream()
                .filter(user -> username.equals(user.username()) && user.getClass().equals(Client.class))
                .map(user -> (Client) user)
                .findFirst();
    }
    public static User getUserByForce(String username) throws UserServiceException {
        return getUser(username)
                .orElseThrow(() -> new UserServiceException(UserServiceException.Type.UserNotFound));
    }
    public static Client getClientByForce(String username) throws UserServiceException {
        return getClient(username)
                .orElseThrow(() -> new UserServiceException(UserServiceException.Type.UserNotFound));
    }
    public static Manager getManagerByForce(String username) throws UserServiceException {
        return getManager(username)
                .orElseThrow(() -> new UserServiceException(UserServiceException.Type.UserNotFound));
    }
    protected static void checkAddressIsValid(Address address) throws UserServiceException {
        if (address == null || address.country() == null || address.city() == null)
            throw new UserServiceException(UserServiceException.Type.InvalidAddress);
    }

    protected static void checkUserExists(String username) throws UserServiceException {
        if (username == null)
            throw new UserServiceException(UserServiceException.Type.InvalidUsername);
        getUser(username).orElseThrow(
                () -> new UserServiceException(UserServiceException.Type.UserNotFound)
        );
    }

    protected static void checkDoesHaveManagerRole(String username) throws UserServiceException {
        if (username == null)
            throw new UserServiceException(UserServiceException.Type.InvalidUsername);
        final User user = getUser(username)
                .orElseThrow(
                        () -> new UserServiceException(UserServiceException.Type.UserNotFound));
        if (!user.getClass().equals(Manager.class)) throw new UserServiceException(UserServiceException.Type.NotManager);
    }

    public static void reset() {
        users.clear();
    }
}

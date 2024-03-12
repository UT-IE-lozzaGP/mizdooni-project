package org.lozza.cli.exceptions;

public class CommandNotFoundException extends Exception {
    @Override
    public String getMessage() {
        return "command not found";
    }
}

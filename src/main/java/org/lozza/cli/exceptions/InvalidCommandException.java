package org.lozza.cli.exceptions;

public class InvalidCommandException extends Exception {
    @Override
    public String getMessage() {
        return "invalid command were given";
    }
}

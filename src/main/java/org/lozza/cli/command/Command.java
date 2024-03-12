package org.lozza.cli.command;

import org.lozza.cli.command.responses.Response;

public interface Command {

    Response run();

    Object body();
}

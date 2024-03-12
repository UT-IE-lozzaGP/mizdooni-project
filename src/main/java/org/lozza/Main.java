package org.lozza;

import org.lozza.cli.CLI;
import org.lozza.cli.command.Command;
import org.lozza.cli.command.responses.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Main {

    private static InputStream inputStream = System.in;
    private static OutputStream outputStream = System.out;

    public static void setInputStream(InputStream inputStream) {
        Main.inputStream = inputStream;
    }

    public static void setOutputStream(OutputStream outputStream) {
        Main.outputStream = outputStream;
    }

    public static void main(String[] args) throws IOException {
        while (true) {
            try {
                final Command command = CLI.getCommand(inputStream, outputStream);
                final Response baseResponse = command.run();
                CLI.showResponse(baseResponse, outputStream);
            } catch (NullPointerException e) {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
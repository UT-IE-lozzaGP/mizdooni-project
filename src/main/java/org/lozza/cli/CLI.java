package org.lozza.cli;

import org.lozza.cli.command.*;
import org.lozza.cli.command.requests.*;
import org.lozza.cli.command.responses.Response;
import org.lozza.cli.exceptions.CommandNotFoundException;
import org.lozza.cli.exceptions.InvalidCommandException;
import org.lozza.json.JSON;

import java.io.*;

public class CLI {

    public static Command getCommand(InputStream inputStream, OutputStream outputStream) throws InvalidCommandException, CommandNotFoundException, IOException {
        final String input = readCommand(inputStream, outputStream);
        return getCommandFromInput(input);
    }

    private static String readCommand(InputStream inputStream, OutputStream outputStream) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        writer.write("> ");
        writer.flush();
        String input;
        while((input = reader.readLine()) == null) {}
        reader.close();
        return input;
    }

    public static Command getCommandFromInput(String input) throws InvalidCommandException, CommandNotFoundException {
        final String[] parts = input.split(" ", 2);
        final String commandType;
        final String jsonBody;
        try {
            commandType = parts[0];
            jsonBody = parts[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException();
        }
        return switch (commandType) {
            case "addUser" -> new AddUserCommand((AddUserBody) JSON.extractJSON(jsonBody, AddUserBody.class));
            case "addRestaurant" -> new AddRestaurantCommand((AddRestaurantBody) JSON.extractJSON(jsonBody, AddRestaurantBody.class));
            case "addTable" -> new AddTableCommand((AddTableBody) JSON.extractJSON(jsonBody, AddTableBody.class));
            case "reserveTable" -> new ReserveTableCommand((ReserveTableBody) JSON.extractJSON(jsonBody, ReserveTableBody.class));
            case "cancelReservation" -> new CancelReservationCommand((CancelReservationBody) JSON.extractJSON(jsonBody, CancelReservationBody.class));
            case "showReservationHistory" -> new ShowReservationHistoryCommand((ShowReservationHistoryBody) JSON.extractJSON(jsonBody, ShowReservationHistoryBody.class));
            case "showAvailableTables" -> new ShowAvailableTablesCommand((ShowAvailableTablesBody) JSON.extractJSON(jsonBody, ShowAvailableTablesBody.class));
            case "searchRestaurantsByName" -> new SearchRestaurantByNameCommand((SearchRestaurantByNameBody) JSON.extractJSON(jsonBody, SearchRestaurantByNameBody.class));
            case "searchRestaurantsByType" -> new SearchRestaurantByTypeCommand((SearchRestaurantByTypeBody) JSON.extractJSON(jsonBody, SearchRestaurantByTypeBody.class));
            case "addReview" -> new AddReviewCommand((AddReviewBody) JSON.extractJSON(jsonBody, AddReviewBody.class));
            default -> throw new CommandNotFoundException();
        };
    }

    /**
     * one '\n' will be added to the end of output
     */
    public static void showResponse(Response response, OutputStream outputStream) throws IOException {
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        writer.write(JSON.serializeJSON(response) + "\n");
        writer.flush();
    }
}

package edu.jssb.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Command {
    ADD_USER("add"),
    DELETE_USER("delete"),
    GET_ALL_USERS("get-all"),
    HELP("help");

    private final String command;

    public static Command stringToCommand(String inputString) {
        Command[] commands = Command.values();
        for (Command command : commands) {
            if (inputString.equals(command.getCommand())) {
                return command;
            }
        }
        throw new UnsupportedOperationException("Invalid command");
    }
}
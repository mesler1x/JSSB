package edu.jssb.service;

import edu.jssb.model.Command;
import edu.jssb.model.Contact;
import edu.jssb.model.Message;
import edu.jssb.model.User;
import edu.jssb.service.parser.ContactParser;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserService {
    ContactParser contactParser;
    Contact contact;
    FileService fileService;

    public String add(String inputData) {
        User user = contactParser.parseStringToUser(inputData);
        contact.add(user);
        fileService.writeUserToFile(user);
        return Message.ACCESS_ADD.getText();
    }

    public String delete(String email) {
        if (!contact.contacts().containsKey(email))
            return Message.NOT_FOUND_USER.getText();
        contact.delete(email);
        return Message.ACCESS_DELETE.getText();
    }

    public String getAll() {
        if (contact.contacts().isEmpty()) {
            return Message.NULL_CONTACTS.getText();
        }
        return contact.contacts()
                .values()
                .stream()
                .map(user -> user.toString() + "\n")
                .collect(Collectors.joining());
    }

    public String showHelp() {
        return Message.HELP.getText();
    }

    public String executeCommand(String inputData) {
        if (inputData.isEmpty()) {
            throw new UnsupportedOperationException("Invalid command");
        }
        String[] params = inputData.split(" ", 2);
        Command command = Command.stringToCommand(params[0].trim());
        return switch (command) {
            case ADD_USER -> add(params[1].trim());
            case DELETE_USER -> delete(params[1].trim());
            case GET_ALL_USERS -> getAll();
            case HELP -> showHelp();
        };
    }
}
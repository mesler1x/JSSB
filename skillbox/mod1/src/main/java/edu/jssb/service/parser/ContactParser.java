package edu.jssb.service.parser;

import edu.jssb.model.User;
import org.springframework.stereotype.Component;

@Component
public class ContactParser {
    public User parseStringToUser(String inputData) {
        String[] userData = inputData.split(";");
        if (userData.length < 3) {
            throw new UnsupportedOperationException("invalid operation");
        }
        return new User(userData[0].trim(), userData[1].trim(), userData[2].trim());
    }
}
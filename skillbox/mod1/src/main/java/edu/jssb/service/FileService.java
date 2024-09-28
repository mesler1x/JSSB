package edu.jssb.service;

import edu.jssb.model.User;
import edu.jssb.service.parser.ContactParser;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@PropertySource("classpath:application.properties")
public class FileService {
    final ContactParser contactParser;

    @Value("${file.path}")
    String filePath;

    public HashMap<String, User> initContacts() {
        HashMap<String, User> contacts = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while (reader.ready()) {
                User user = contactParser.parseStringToUser(reader.readLine());
                contacts.put(user.getEmail(), user);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return contacts;
    }

    public void writeUserToFile(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.newLine();
            writer.write(String.format("%s;%s;%s", user.getFullName(), user.getPhoneNumber(), user.getEmail()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
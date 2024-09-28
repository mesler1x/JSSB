package edu.jssb.model;

import java.util.HashMap;

public record Contact(HashMap<String, User> contacts) {
    public void add(User user) {
        contacts.put(user.getEmail(), user);
    }

    public void delete(String email) {
        contacts.remove(email);
    }
}
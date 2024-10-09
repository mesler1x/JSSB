package edu.mod2_skillbox.domain;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class UserStorage {
    private final HashMap<Long, User> users;

    public UserStorage() {
        this.users = new HashMap<>();
    }

    public UserStorage(List<User> users) {
        this.users = new HashMap<>();
        for (User user : users) {
            add(user);
        }
    }

    public void add(User user) {
        users.put(user.getId(), user);
    }

    public void delete(Long id) {
        users.remove(id);
    }

    public void deleteAll() {
        users.clear();
    }

    public List<User> getAll() {
        return users.values().stream().toList();
    }
}
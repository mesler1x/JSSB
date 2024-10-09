package edu.mod2_skillbox.shell;

import edu.mod2_skillbox.domain.User;
import edu.mod2_skillbox.domain.UserStorage;
import edu.mod2_skillbox.eventlistener.event.AddUserEvent;
import edu.mod2_skillbox.eventlistener.event.DeleteUserEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class MainShell {
    private final UserStorage userStorage;
    private final ApplicationEventPublisher applicationEventPublisher;

    @ShellMethod
    public String getAll() {
        if (userStorage.getAll().isEmpty()) {
            return "List users is empty";
        }
        var stringUsers = new StringBuilder();
        for (var user : userStorage.getAll()) {
            stringUsers.append(user.toString()).append("\n");
        }
        return stringUsers.toString();
    }

    @ShellMethod
    public void add(String firstName, String secondName, int age) {
        var user = new User(firstName, secondName, age);
        applicationEventPublisher.publishEvent(new AddUserEvent(user.toString()));
        userStorage.add(user);
    }

    @ShellMethod
    public void deleteByUID(Long id) {
        applicationEventPublisher.publishEvent(new DeleteUserEvent(id.toString()));
        userStorage.delete(id);
    }

    @ShellMethod
    public String deleteAll() {
        userStorage.deleteAll();
        return "Users storage cleared";
    }
}

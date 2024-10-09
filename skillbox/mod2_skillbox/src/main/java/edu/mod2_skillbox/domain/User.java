package edu.mod2_skillbox.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;

    public User(String firstName, String lastName, int age) {
        this.id = new Random().nextLong(2000);
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}

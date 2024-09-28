package edu.jssb.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    String fullName;
    String phoneNumber;
    String email;

    @Override
    public String toString() {
        return String.format("%s|%s|%s", fullName, phoneNumber, email);
    }
}
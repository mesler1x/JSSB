package edu.mod3_skillbox.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    Long id;
    String firstName;
    String secondName;
    String email;
    String phoneNumber;
}
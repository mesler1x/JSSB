package edu.jssb.config;

import edu.jssb.model.Contact;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.HashMap;

@Configuration
@Profile("default")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class DefaultConfig {

    @Bean
    public Contact contacts() {
        return new Contact(new HashMap<>());
    }
}
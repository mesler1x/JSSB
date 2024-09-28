package edu.jssb.config;

import edu.jssb.model.Contact;
import edu.jssb.service.FileService;
import edu.jssb.service.parser.ContactParser;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("init")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class InitConfig {

    @Bean
    public ContactParser contactParser() {
        return new ContactParser();
    }

    @Bean
    public FileService fileService() {
        return new FileService(contactParser());
    }

    @Bean
    public Contact contacts() {
        return new Contact(fileService().initContacts());
    }
}
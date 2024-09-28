package edu.jssb.config;

import edu.jssb.model.Contact;
import edu.jssb.service.FileService;
import edu.jssb.service.UserService;
import edu.jssb.service.parser.ContactParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("edu.jssb")
@PropertySource("classpath:application.properties")
@RequiredArgsConstructor
public class ApplicationConfig {
    @Autowired
    private Contact contacts;
    @Autowired
    private FileService fileService;

    @Bean
    public ContactParser contactParser() {
        return new ContactParser();
    }

    @Bean
    public UserService userService() {
        return new UserService(contactParser(), contacts, fileService);
    }
}
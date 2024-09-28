package edu.jssb;

import edu.jssb.config.ApplicationConfig;
import edu.jssb.model.Message;
import edu.jssb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RequiredArgsConstructor
@ComponentScan("edu.jssb")
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        UserService service = context.getBean(UserService.class);
        System.out.println(Message.HELLO.getText());
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.println(service.executeCommand(reader.readLine()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
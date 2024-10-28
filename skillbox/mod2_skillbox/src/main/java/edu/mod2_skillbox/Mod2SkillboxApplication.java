package edu.mod2_skillbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class Mod2SkillboxApplication {

    public static void main(String[] args) {
        SpringApplication.run(Mod2SkillboxApplication.class, args);
    }

}

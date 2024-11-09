package edu.mod5_skillbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Mod5SkillboxApplication {

    public static void main(String[] args) {
        SpringApplication.run(Mod5SkillboxApplication.class, args);
    }

}

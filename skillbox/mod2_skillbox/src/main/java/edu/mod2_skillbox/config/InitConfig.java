package edu.mod2_skillbox.config;

import edu.mod2_skillbox.domain.UserStorage;
import edu.mod2_skillbox.common.MyParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("init")
public class InitConfig {
    private final MyParser myParser;

    public InitConfig(MyParser myParser) {
        this.myParser = myParser;
    }

    @Bean
    public UserStorage userStorage() {
        return new UserStorage(myParser.parseInitFile());
    }

}
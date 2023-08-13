package me.mourjo.jamboree;

import me.mourjo.jamboree.data.PartyRepository;
import me.mourjo.jamboree.data.PartyRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JamboreeApplication {

    public static void main(String[] args) {
        SpringApplication.run(JamboreeApplication.class, args);
    }

    @Bean
    public PartyRepository PartyRepositoryFactory() {
        return new PartyRepositoryImpl();
    }

}

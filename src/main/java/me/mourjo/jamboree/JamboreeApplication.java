package me.mourjo.jamboree;

import me.mourjo.jamboree.data.PartyRepository;
import me.mourjo.jamboree.data.PartyRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.Executors;

@SpringBootApplication
public class JamboreeApplication {

    private static Logger log = LoggerFactory.getLogger(JamboreeApplication.class);

    public static void main(String[] args) {
        log.info("Starting application");
        SpringApplication.run(JamboreeApplication.class, args);
        log.info("Started application");
    }

    @Bean
    public PartyRepository PartyRepositoryFactory() {
        return new PartyRepositoryImpl();
    }

}

package me.mourjo.jamboree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JamboreeApplication {

    private static final Logger logger = LoggerFactory.getLogger(JamboreeApplication.class);

    public static void main(String[] args) {
        logger.info("Starting application");
        SpringApplication.run(JamboreeApplication.class, args);
        logger.info("Started application");
    }
}

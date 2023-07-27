package dev.aleixmorgadas.eventsourcedmongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventSourcedMongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventSourcedMongoApplication.class, args);
    }

}

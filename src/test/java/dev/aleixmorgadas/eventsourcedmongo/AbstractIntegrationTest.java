package dev.aleixmorgadas.eventsourcedmongo;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public class AbstractIntegrationTest {
    @Container
    @ServiceConnection
    static MongoDBContainer mongo = new MongoDBContainer(DockerImageName.parse("mongo:6.0.7"));
}

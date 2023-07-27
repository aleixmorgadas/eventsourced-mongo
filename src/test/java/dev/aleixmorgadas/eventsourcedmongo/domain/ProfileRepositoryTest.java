package dev.aleixmorgadas.eventsourcedmongo.domain;

import dev.aleixmorgadas.eventsourcedmongo.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class ProfileRepositoryTest extends AbstractIntegrationTest {
    @Autowired
    ProfileRepository repository;

    @Test
    void savesProfileIntoDB() {
        var profile = Profile.of("Aleix");
        profile.withAge(30);
        repository.save(profile);

        var id = profile.id;

        var savedProfile = repository.findById(id).orElseThrow();
        assertThat(savedProfile.events).hasSize(2);
        assertThat(savedProfile.name()).isEqualTo("Aleix");
        assertThat(savedProfile.age()).isEqualTo(30);
    }
}

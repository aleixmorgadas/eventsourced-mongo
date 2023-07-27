package dev.aleixmorgadas.eventsourcedmongo.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfileTest {

    @Test
    void createsAProfile() {
        var profile = Profile.of("Aleix");

        assertThat(profile.name()).isEqualTo("Aleix");
        assertThat(profile.events).hasSize(1);
    }

    @Test
    void addsAge() {
        var profile = Profile.of("Aleix");
        profile.withAge(30);

        assertThat(profile.age()).isEqualTo(30);
        assertThat(profile.events).hasSize(2);
        assertThat(profile.events.get(1)).isInstanceOf(Profile.Event.AgeUpdated.class);
    }
}

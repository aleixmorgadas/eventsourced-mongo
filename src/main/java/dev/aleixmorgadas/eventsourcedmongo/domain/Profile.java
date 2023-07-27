package dev.aleixmorgadas.eventsourcedmongo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document
public class Profile {
    @Id
    String id;

    @Transient
    final State state = new State();
    final List<Event> events = new ArrayList<>();

    public Profile(String id, String name) {
        on(new Event.Created(id, name));
    }

    @PersistenceCreator
    Profile(String id, List<Event> events) {
        this.id = id;
        events.forEach(this::apply);
    }

    void apply(Event event) {
        events.add(event);
    }

    void on(Event.Created created) {
        events.add(created);
        id = created.id;
        state.name = created.name;
    }

    void on(Event.AgeUpdated ageUpdated) {
        events.add(ageUpdated);
        state.age = ageUpdated.age();
    }

    String name() {
        return state.name;
    }

    public static Profile of(String name) {
        return new Profile(UUID.randomUUID().toString(), name);
    }

    public int age() {
        return state.age;
    }

    public void withAge(int age) {
        on(new Event.AgeUpdated(age));
    }

    static class State {
        String name;
        int age;
    }

    sealed interface Event {
        record Created(String id, String name) implements Event {}
        record AgeUpdated(int age) implements Event {}
    }
}

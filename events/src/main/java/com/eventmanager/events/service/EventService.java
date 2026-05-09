package com.eventmanager.events.service;

import com.eventmanager.events.model.Category;
import com.eventmanager.events.model.Event;
import com.eventmanager.events.model.Location;
import com.eventmanager.events.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service

public class EventService {

    private List<Event> events;
    private long idCounter = 0;

    public EventService () {

        events = new ArrayList<>();

        User user1 = new User("Aleksandra", "aleks@gmail.com");
        User user2 = new User("Marko", "marko@gmail.com");

        Event e1 = new Event(
                1L,
                "Java konferencija",
                LocalDateTime.of(2026, 6, 15, 18, 0),
                Category.SEMINAR,
                new Location("Beograd", "Knez Mihailova 10", "Sava Centar"),
                user1,
                new ArrayList<>()
        );

        Event e2 = new Event(
                2L,
                "Gaming turnir",
                LocalDateTime.of(2026, 7, 2, 20, 0),
                Category.OTHER,
                new Location("Novi Sad", "Bulevar Oslobodjenja 25", "SPENS"),
                user2,
                new ArrayList<>()
        );

        Event e3 = new Event(
                3L,
                "Rock koncert",
                LocalDateTime.of(2026, 8, 10, 21, 30),
                Category.CONCERT,
                new Location("Nis", "Generala Milojka Lesjanina 5", "Tvrdjava"),
                user1,
                new ArrayList<>()
        );

        Event e4 = new Event(
                4L,
                "Sajam knjiga",
                LocalDateTime.of(2026, 9, 20, 12, 0),
                Category.OTHER,
                new Location("Beograd", "Bulevar Vojvode Misica", "Beogradski sajam"),
                user2,
                new ArrayList<>()
        );

        events.add(e1);
        events.add(e2);
        events.add(e3);
        events.add(e4);
    }

    public void addEvent(Event event) {
        event.setId(++idCounter);
        events.add(event);
    }

    public List<Event> getAllEvents() {
        return new ArrayList<>(events); //ista stvar kao return events, ali je ovako profesionalnije vratiti referencu na objekat
    }

    public boolean deleteEvent(Long id) {
        return events.removeIf(e -> e.getId().equals(id));
    }

    public boolean updateEvent(Event event) {

        for (Event e : events ) {
            if (e.getId().equals(event.getId())) {
                e.setName(event.getName());
                e.setLocation(event.getLocation());
                e.setCategory(event.getCategory());
                e.setDateTime(event.getDateTime());

                return true;
            }
        }

        return false;

    }

    public List<Event> getEventsByUser (User user) {
        List<Event> userEvents = new ArrayList<>();

        for(Event e : events) {
            if (e.getOrganizer().equals(user)) {
                userEvents.add(e);
            }
        }

        return userEvents;
    }

    public Event getEventById (Long id) {
        Event forShow;
        for (Event e : events) {
            if (Objects.equals(e.getId(), id)) {
                forShow = e;
                return forShow;
            }
        }
        return null;
    }
}

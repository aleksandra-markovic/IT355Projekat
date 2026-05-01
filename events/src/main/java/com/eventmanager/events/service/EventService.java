package com.eventmanager.events.service;

import com.eventmanager.events.model.Event;
import com.eventmanager.events.model.User;

import java.util.ArrayList;
import java.util.List;

public class EventService {

    private List<Event> events;
    private long idCounter = 0;

    public EventService () {
        events = new ArrayList<>();
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
}

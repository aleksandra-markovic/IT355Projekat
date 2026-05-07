package com.eventmanager.events.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Event {
    private Long id;
    private String name;
    private LocalDateTime dateTime;
    private Category category;
    private Location location;
    private User organizer;
    private List<User> attendees;

    @Override
    public boolean equals (Object o) {
        if(o == null) {
            return false;
        }

        if (this == o) return true;
        if (getClass() != o.getClass()) return false;

        Event e = (Event) o;

        return id != null ? id.equals(e.id) : e.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

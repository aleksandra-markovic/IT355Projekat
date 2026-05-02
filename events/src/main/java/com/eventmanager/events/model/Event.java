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
}

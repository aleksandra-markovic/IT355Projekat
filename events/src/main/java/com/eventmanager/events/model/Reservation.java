package com.eventmanager.events.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Reservation {
    private User user;
    private Event event;
    private LocalDateTime createdAt;
}

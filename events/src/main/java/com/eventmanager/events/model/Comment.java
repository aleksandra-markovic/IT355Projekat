package com.eventmanager.events.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Comment {
    private String text;
    private User author;
    private Event event;
}

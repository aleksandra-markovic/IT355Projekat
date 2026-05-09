package com.eventmanager.events.service;

import com.eventmanager.events.model.Comment;
import com.eventmanager.events.model.Event;
import com.eventmanager.events.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class CommentService {

    private List<Comment> comments;

    private final EventService eventService;

    public CommentService (EventService eventService) {
        this.eventService = eventService;

        comments = new ArrayList<>();

        User user1 = new User("Aleksandra", "aleksandra@gmail.com");
        User user2 = new User("Anja", "anja@gmail.com");
        User user3 = new User("Marko", "marko@gmail.com");


        Event e1 = eventService.getEventById(1L);
        Event e2 = eventService.getEventById(2L);
        Event e3 = eventService.getEventById(3L);
        Event e4 = eventService.getEventById(4L);

        Comment c1 = new Comment(
                "Jedva čekam ovaj događaj!",
                user2,
                e1
        );

        Comment c2 = new Comment(
                "Bila sam prošle godine, bilo je odlično.",
                user1,
                e1
        );

        Comment c3 = new Comment(
                "Dolazim sa ekipom!",
                user1,
                e2
        );

        Comment c4 = new Comment(
                "Koji bendovi nastupaju?",
                user2,
                e3
        );

        Comment c5 = new Comment(
                "Biće odlično!",
                user3,
                e4
        );

        comments.add(c1);
        comments.add(c2);
        comments.add(c3);
        comments.add(c4);
        comments.add(c5);
    }

    public void addComment (Comment comment) {
        comments.add(comment);
    }

    public List<Comment> getCommentsByEvent (Event event) {
        List <Comment> eventComments = new ArrayList<>();
        for (Comment c : comments) {
            if (c.getEvent().equals(event)) {
                eventComments.add(c);
            }
        }

        return eventComments;
    }
}

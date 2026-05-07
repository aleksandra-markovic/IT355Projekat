package com.eventmanager.events.service;

import com.eventmanager.events.model.Comment;
import com.eventmanager.events.model.Event;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class CommentService {

    private List<Comment> comments;

    public CommentService () {
        comments = new ArrayList<>();
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

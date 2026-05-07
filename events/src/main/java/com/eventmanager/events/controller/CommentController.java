package com.eventmanager.events.controller;

import com.eventmanager.events.model.Comment;
import com.eventmanager.events.service.CommentService;
import com.eventmanager.events.service.EventService;
import com.eventmanager.events.util.SessionUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {

    private final EventService eventService;
    private final CommentService commentService;

    public CommentController(EventService eventService, CommentService commentService) {
        this.eventService = eventService;
        this.commentService = commentService;
    }

    @PostMapping("/events/{id}/comments")
    public String postComment (@PathVariable Long id, String text) {
        Comment comment = new Comment(text, SessionUser.currentUser, eventService.getEventById(id));

        commentService.addComment(comment);

        return "redirect:/events/{id}";
    }
}

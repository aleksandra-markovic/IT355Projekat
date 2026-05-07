package com.eventmanager.events.controller;

import com.eventmanager.events.model.Comment;
import com.eventmanager.events.model.Event;
import com.eventmanager.events.service.CommentService;
import com.eventmanager.events.service.EventService;
import com.eventmanager.events.util.SessionUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller

public class EventController {
    private final EventService eventService;
    private final CommentService commentService;

    public EventController (EventService eventService, CommentService commentService) {
        this.eventService = eventService;
        this.commentService = commentService;
    }

    @GetMapping("/events")
    public String getAllEvents(Model model) {
        List<Event> events = eventService.getAllEvents();

        model.addAttribute("events", events);

        return "events/list";
    }

    @GetMapping("/events/new")
    public String showCreateEventForm() {
        return "events/form";
    }

    @PostMapping("/events")
    public String createEvent(Event event) {
        event.setOrganizer(SessionUser.currentUser);
        eventService.addEvent(event);
        return "redirect:/events";
    }

    @PostMapping("/events/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        if (eventService.deleteEvent(id)) {
            return "redirect:/events";
        } else {
            return "redirect:/events?error=true";
        }

    }

    @GetMapping("/events/{id}")
    public String getEventDetails (@PathVariable Long id, Model model) {
        Event event = eventService.getEventById(id);

        if (event == null) {
            return "redirect:/events";
        }

        List<Comment> comments = commentService.getCommentsByEvent(event);

        model.addAttribute("event", event);
        model.addAttribute("comments", comments);

        return "events/details";
    }

    @GetMapping("/events/edit/{id}")
    public String editEvent(@PathVariable Long id, Model model) {
        Event event = eventService.getEventById(id);

        if (event == null) {
            return "redirect:/events";
        }

        model.addAttribute("event", event);

        return "events/edit-form";
    }

    @PostMapping("/events/update")
    public String updateEvent (Event event) {
        eventService.updateEvent(event);

        return "redirect:/events";
    }

    @GetMapping("/my-events")
    public String getUserEvents (Model model) {
        if (SessionUser.currentUser == null) {
            return "redirect:/login";
        }
        List<Event> userEvents = eventService.getEventsByUser(SessionUser.currentUser);

        model.addAttribute("userEvents", userEvents);

        return "events/my-events";
    }
}

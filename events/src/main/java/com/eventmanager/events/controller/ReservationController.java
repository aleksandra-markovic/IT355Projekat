package com.eventmanager.events.controller;

import com.eventmanager.events.model.Reservation;
import com.eventmanager.events.service.EventService;
import com.eventmanager.events.service.ReservationService;
import com.eventmanager.events.util.SessionUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ReservationController {

    private final ReservationService reservationService;
    private final EventService eventService;

    public ReservationController(ReservationService reservationService, EventService eventService) {
        this.reservationService = reservationService;
        this.eventService = eventService;
    }

    @GetMapping("/my-reservations")
    public String getUserReservations (Model model) {
        List<Reservation> reservations = reservationService.getReservationsByUser(SessionUser.currentUser);

        model.addAttribute("reservations", reservations);

        return "my-reservations";
    }

    @PostMapping("/events/{id}/reserve")
    public String addUserReservation (@PathVariable Long id) {
        Reservation reservation = new Reservation(SessionUser.currentUser, eventService.getEventById(id), LocalDateTime.now());
        boolean done = reservationService.addReservation(reservation);

        if (!done) {
            return "redirect:/events/{id}?error=true";
        }

        return "redirect:/events/{id}?reserved=true";
    }

    @PostMapping("/my-reservations/{id}/delete")
    public String deleteUserReservation (@PathVariable Long id) {
        boolean done = reservationService.deleteReservation(SessionUser.currentUser, eventService.getEventById(id));

        if(!done) {
            return "redirect:/my-reservations?error=true";
        }

        return "redirect:/my-reservations?deleted=true";
    }
}

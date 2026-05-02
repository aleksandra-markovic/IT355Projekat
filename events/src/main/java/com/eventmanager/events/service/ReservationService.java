package com.eventmanager.events.service;

import com.eventmanager.events.model.Event;
import com.eventmanager.events.model.Reservation;
import com.eventmanager.events.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class ReservationService {

    private List<Reservation> reservations;

    public ReservationService () {
        reservations = new ArrayList<>();
    }

    public boolean addReservation (Reservation reservation) {
        for (Reservation r : reservations) {
            if (r.getUser().equals(reservation.getUser())
                    && r.getEvent().equals(reservation.getEvent())) {
                return false;
            }
        }
        reservations.add(reservation);
        return true;
    }

    public boolean deleteReservation (User user, Event event) {
        return reservations.removeIf(reservation -> reservation.getUser().equals(user) && reservation.getEvent().equals(event));
    }

    public List<Reservation> getReservationsByUser(User user) {
        List<Reservation> userReservations = new ArrayList<>();

        for (Reservation r : reservations) {
            if (r.getUser().equals(user)) {
               userReservations.add(r);
            }
        }

        return userReservations;
    }
}

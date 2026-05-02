package com.eventmanager.events.service;

import com.eventmanager.events.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class UserService {
    private List<User> users;

    public UserService () {
        users = new ArrayList<>();

        users.add(new User("Aleksandra", "aleksandra@gmail.com"));
        users.add(new User("Anja", "anja@gmail.com"));
    }

    public User findUserByEmail (String email) {
        for (User u : users) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }

        return null;
    }
}

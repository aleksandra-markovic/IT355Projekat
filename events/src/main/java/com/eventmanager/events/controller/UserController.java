package com.eventmanager.events.controller;

import com.eventmanager.events.model.User;
import com.eventmanager.events.service.UserService;
import com.eventmanager.events.util.SessionUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage () {
        return "login";
    }

    @PostMapping("/login")
    public String login (String email) {
        User user = userService.findUserByEmail(email);

        if (user == null) {
            return "redirect:/login";
        }

        SessionUser.currentUser = user;

        return "redirect:/events";
    }
}

package ch.fhnw.pizza.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

    @GetMapping("/myprofile")
    public String getProfile() {
        // Here you can return the user's profile information.
        // Fetching user's profile from your database is what is needed.
        return "User Profile";
    }
}
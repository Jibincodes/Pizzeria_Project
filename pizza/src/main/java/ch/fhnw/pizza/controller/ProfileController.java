package ch.fhnw.pizza.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myprofile")
public class ProfileController {

    @GetMapping
    public String getProfile() {
        // Here you can return the user's profile information.
        // Fetching user's profile from your database is what is needed.
        return "User Profile";
    }

    //to show all user details from domain user
    /*@GetMapping("/users")
    public String getUsers() {
        // Here you can return the user's profile information.
        // Fetching user's profile from your database is what is needed.
        return "User Profile";
    }*/

}
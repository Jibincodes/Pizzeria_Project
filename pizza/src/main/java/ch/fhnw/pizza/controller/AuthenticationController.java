package ch.fhnw.pizza.controller;

import org.springframework.web.bind.annotation.RestController;

import ch.fhnw.pizza.data.domain.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class AuthenticationController {

    @PostMapping("/login") // changed from Getmapping 
    //public String getLogin(@RequestParam String username, @RequestParam String password) {
        public String getLogin(@RequestBody User user) {    
        //if (username.equals("admin") && password.equals("password")) {
        if (user.getUserName().equals("admin") && user.getPassword().equals("password")) {    
            return "Login successful!";
        } else {
            return "Invalid credentials!";
        }
    }
    
}
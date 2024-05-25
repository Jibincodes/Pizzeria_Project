package ch.fhnw.pizza.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.fhnw.pizza.business.service.MenuService;
import ch.fhnw.pizza.business.service.UserService;
import ch.fhnw.pizza.data.domain.User;

@RestController
@RequestMapping("/myprofile")
public class ProfileController {

     @Autowired
    private UserService userservice;

   /*  @GetMapping
    public String getProfile() {
        // Here you can return the user's profile information.
        // Fetching user's profile from your database is what is needed.
        return "User Profile";
    }*/

    //to show all user details from domain user
   /* @RequestMapping("/{username}")
    @GetMapping(produces = "application/json")
    public ResponseEntity<User> getUserProfile(@PathVariable String userName) {
        User user = userservice.findByUsername(userName);
        
        if (user != null) {
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    } */
    @GetMapping(path="/{username}", produces = "application/json")
    public ResponseEntity getUserProfile(@PathVariable String username) {
        User user = userservice.findByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user was found");
        }
    }

}
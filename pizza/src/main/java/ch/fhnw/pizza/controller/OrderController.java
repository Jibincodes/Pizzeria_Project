
package ch.fhnw.pizza.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ch.fhnw.pizza.data.domain.Order;

@RestController
public class OrderController {
    
    @PostMapping("/orders")
    public String placeOrder(@RequestBody Order order) {
        // Logic to process the order
        // ...// create order
        
        return "Order placed successfully!";
    }
}

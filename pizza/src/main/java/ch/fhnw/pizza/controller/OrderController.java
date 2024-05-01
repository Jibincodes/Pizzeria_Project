
package ch.fhnw.pizza.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ch.fhnw.pizza.data.domain.Order;

@RestController
public class OrderController {
    
    @PostMapping("/order")
    public String placeOrder(@RequestBody Order order) {
        // Logic to process the order
        //create order
     //   List<Order> orders = // Logic to fetch all orders from the database or any other data source

        // Convert the list of orders to a string representation
    //    String ordersString = orders.toString();

     //   return ordersString;
        
        return "Order placed successfully!";
    }
}

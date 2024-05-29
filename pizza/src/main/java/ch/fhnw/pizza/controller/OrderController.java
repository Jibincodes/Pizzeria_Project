package ch.fhnw.pizza.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import ch.fhnw.pizza.business.service.OrderService;
import ch.fhnw.pizza.business.service.UserService;
import ch.fhnw.pizza.data.domain.Order;
import ch.fhnw.pizza.data.domain.User;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping(path="/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity placeOrder(@RequestBody Order order) {
        try {
            User currentUser = userService.getCurrentUser();
            order.setUser(currentUser);
            order = orderService.addOrder(order);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Order already exists with given id");
        }
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity getOrder(@PathVariable Long id) {
        try {
            Order order = orderService.findOrderById(id);
            User currentUser = userService.getCurrentUser();
            if (order.getUser().getId().equals(currentUser.getId())) {
                return ResponseEntity.ok(order);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to view this order");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No order found with given id");
        }
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity getOrderList() {
        User currentUser = userService.getCurrentUser();
        List<Order> orderList = orderService.getOrdersByUser(currentUser);
        return ResponseEntity.ok(orderList);
    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity updateOrder(@PathVariable Long id, @RequestBody Order order) {
        try {
            Order existingOrder = orderService.findOrderById(id);
            User currentUser = userService.getCurrentUser();
            if (!existingOrder.getUser().getId().equals(currentUser.getId())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to update this order");
            }
            order = orderService.updateOrder(id, order);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No order found with given id");
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteOrder(@PathVariable Long id) {
        try {
            Order order = orderService.findOrderById(id);
            User currentUser = userService.getCurrentUser();
            if (!order.getUser().getId().equals(currentUser.getId())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to delete this order");
            }
            orderService.deleteOrder(id);
            return ResponseEntity.ok("Order with id " + id + " deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No order found with given id");
        }
    }
    
    
    
    
    // idea is to CRUD same as menu for orders
    // i.e. get , post, put, delete for orders

    // also maybe final price calculation in order controller which is displayed in checkout cart
}
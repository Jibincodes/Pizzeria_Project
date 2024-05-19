
package ch.fhnw.pizza.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.fhnw.pizza.business.service.OrderService;
import ch.fhnw.pizza.data.domain.Order;

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

    @PostMapping(consumes="application/json", produces="application/json")
    public ResponseEntity placeOrder(@RequestBody Order order) {
        try{
            order = orderService.addOrder(order);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Order already exists with given id");
        }
        return ResponseEntity.ok(order);
    }

    @GetMapping(path="/{id}", produces = "application/json")
    public ResponseEntity getOrder(@RequestParam Long id) {
        try{
            Order order = orderService.findOrderById(id);
            return ResponseEntity.ok(order);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No order found with given id");
        }
    }

    @GetMapping(path="/order", produces = "application/json")
    public List<Order> getOrderList() {
        List<Order> orderList = orderService.getAllOrders();

        return orderList;
    }

    @PutMapping(path="/{id}", consumes="application/json", produces = "application/json")
    public ResponseEntity updateOrder(@PathVariable Long id, @RequestBody Order order) {
        try{
            order = orderService.updateOrder(id, order);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No order found with given id");

        }
        return ResponseEntity.ok(order);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity deleteOrder(@PathVariable Long id) {
        try{
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

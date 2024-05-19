package ch.fhnw.pizza.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.fhnw.pizza.data.domain.Order;
import ch.fhnw.pizza.data.domain.Pizza;
import ch.fhnw.pizza.data.repository.OrderRepository;

@Service
public class OrderService {

@Autowired
private OrderRepository orderRepository;

   //The OrderService should have methods to add, update, delete and get orders

   //Finding order by id
   public Order findOrderById(Long id) {
     try {
         Order order = orderRepository.findById(id).get();
         return order;
     } catch (Exception e) {
         throw new RuntimeException("Order with id " + id + " not found");
     } 
    }

    //adding order
    public Order addOrder(Order order) throws Exception
    {
        if(order.getFinalprice() != null) {
            return orderRepository.save(order);
        }
        throw new Exception("Invalid order");
    }

    //updating order
    public Order updateOrder(Long id, Order order) throws Exception {
        Order orderToUpdate = orderRepository.findById(id).get();
        if(orderToUpdate != null) {
            if(order.getFinalprice() != null)
                orderToUpdate.setFinalprice(order.getFinalprice());
            return orderRepository.save(orderToUpdate);
        }
        throw new Exception("Order with id " + id + " does not exist");
    }

    //deleting order
    public void deleteOrder(Long id) throws Exception {
        if(orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else
            throw new Exception("Order with id " + id + " does not exist");
    }

    //get all orders
      public List<Order> getAllOrders() {
       // List<Order> orderList = orderRepository.findAll();
        //return orderList;
        return orderRepository.findAll();
      }
      

}

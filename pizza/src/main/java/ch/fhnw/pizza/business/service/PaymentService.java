package ch.fhnw.pizza.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.fhnw.pizza.data.domain.Order;
import ch.fhnw.pizza.data.domain.Payment;
import ch.fhnw.pizza.data.repository.OrderRepository;
import ch.fhnw.pizza.data.repository.PaymentRepository;

@Service
public class PaymentService {

@Autowired
private PaymentRepository paymentRepository;

@Autowired
private OrderRepository orderRepository;

@Transactional
public Payment addPayment(Long orderId, Payment payment) throws Exception {
    // Find the order by ID
    Order order = orderRepository.findById(orderId).orElseThrow(() -> new Exception("Order not found"));

    // Associate the payment with the order
    payment.setOrder(order);
    payment.setCompleted(true); // Mark the payment as completed

    // Save the payment
    return paymentRepository.save(payment);
}

@Transactional
public Payment addPayment(Payment payment) throws Exception {
    if (payment.getOrder() == null) {
        throw new Exception("Order cannot be null");
    }
    return paymentRepository.save(payment);
}
    //public Payment addPayment(Payment payment) {
        // You can perform any additional logic here before saving the payment
        // For example, you might want to validate the payment details before saving

      //  return paymentRepository.save(payment);
    //}
    
}

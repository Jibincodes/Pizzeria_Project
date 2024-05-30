package ch.fhnw.pizza.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.fhnw.pizza.data.domain.Payment;
import ch.fhnw.pizza.data.repository.PaymentRepository;

@Service
public class PaymentService {

@Autowired
private PaymentRepository paymentRepository;

@Transactional
    public Payment addPayment(Payment payment) {
        // You can perform any additional logic here before saving the payment
        // For example, you might want to validate the payment details before saving

        return paymentRepository.save(payment);
    }
    
}

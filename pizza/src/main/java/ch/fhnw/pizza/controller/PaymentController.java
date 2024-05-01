package ch.fhnw.pizza.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
     
    @GetMapping(value="/paymentconfirmation")
    public String getConfirmation() {
        return "Your payment was successful! Thank you for ordering with us!";
    }

}


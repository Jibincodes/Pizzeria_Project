package ch.fhnw.pizza.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.fhnw.pizza.business.service.PaymentService;
import ch.fhnw.pizza.data.domain.Payment;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class PaymentController {

  @Autowired
  private PaymentService paymentService; 
     
  //  @GetMapping(value="/paymentconfirmation")
  //  public String getConfirmation() {
   //     return "Your payment was successful! Thank you for ordering with us!";
    //}

    //posting to confirm the payment
    /*@PostMapping (value="/paymentconfirmation")
    public String postConfirmation() {
        return "Your payment was successful! Thank you for ordering with us!";
    }*/
  @PostMapping("/confirm/{orderId}")
    public ResponseEntity<String> postConfirmation(@PathVariable Long orderId, @RequestBody Payment payment) {
        try {
            Payment savedPayment = paymentService.addPayment(orderId, payment);
            return ResponseEntity.ok("Your payment of " + savedPayment.getFinalprice() + " was successful! Thank you for ordering with us!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }  

    
    
}


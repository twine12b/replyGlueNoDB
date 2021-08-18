package com.replyglue.app.web;

import com.replyglue.app.domain.Payment;
import com.replyglue.app.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class PaymentController {

    @Autowired
    private PaymentService paymentService;


    @PostMapping("/payments")
    public ResponseEntity<String> makePayment(@RequestBody Payment payment) {

        return paymentService.creditCardIsValid(payment.getPayment_card()) ?
                paymentService.isValidAmount(payment.getAmount()) ?
                        (paymentService.findUserByCreditCard(payment.getPayment_card()) != null) ?

                                paymentService.makePayment(payment)?new ResponseEntity<>("Payment Received", HttpStatus.CREATED)
                                        : new ResponseEntity<>("Please check payment details", HttpStatus.BAD_REQUEST)

                            : new ResponseEntity<>("Card Not registered to any user", HttpStatus.NOT_FOUND)
                        : new ResponseEntity<>("Check payment amount - [3] digits", HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>("Check card details", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/payments/findcard")
    public ResponseEntity<String> findUserByCreditCard(@RequestParam("number") String card_details){
        return paymentService.checkCardExists(Long.parseLong(card_details))?
              new ResponseEntity<>("Payment Received", HttpStatus.CREATED) : new ResponseEntity<>("Card Number Not Valid", HttpStatus.NOT_FOUND);
    }
}

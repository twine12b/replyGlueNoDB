package com.replyglue.app.web;

import com.replyglue.app.domain.Payment;
import com.replyglue.app.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

//    @PostMapping(value = "/payments", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @PostMapping("/payments")
    public ResponseEntity makePayment(@RequestBody Payment payment) {
//        return new ResponseEntity(HttpStatus.CREATED);

        return paymentService.makePayment(payment)?
                new ResponseEntity("Payment Received", HttpStatus.CREATED) : new ResponseEntity("Please check payment details", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/payments/findcard")
    public ResponseEntity findUserByCreditCard(@RequestParam("number") String card_details){
        return paymentService.checkCardExists(Long.parseLong(card_details))?
              new ResponseEntity("Payment Received", HttpStatus.CREATED) : new ResponseEntity("Card Number Not Valid", HttpStatus.NOT_FOUND);
    }

}

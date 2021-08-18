package com.replyglue.app.web;

import com.replyglue.app.domain.User;
import com.replyglue.app.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class RegistrationController {

    @Autowired
    private final RegistrationService registrationService;

    @GetMapping()
    public List<User> getUsersByFilter(@RequestParam("CreditCard") Optional<String> yesNoAll) {
        return yesNoAll.isPresent()? registrationService.findUsersWithCreditCard(yesNoAll.get()) :
                registrationService.findUsersWithCreditCard("all");
    }

    @PostMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity validateUser(@RequestBody User user) {
        var responseEntity = registrationService.isUserValid(user)  ?
                registrationService.isRegisteredUser(user.getUsername()) ? new ResponseEntity("User already registered.", HttpStatus.CONFLICT) :
                        registrationService.isAdult(user.getDob())?
                           registrationService.registerUser(user) ? new ResponseEntity("["+user.getUsername() +"] is now registered", HttpStatus.CREATED) : new ResponseEntity(HttpStatus.BAD_REQUEST)
                                : new ResponseEntity("You must be 18 or older to register.", HttpStatus.FORBIDDEN)
                : new ResponseEntity("User name contains ILLEGAL character/s.", HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

}

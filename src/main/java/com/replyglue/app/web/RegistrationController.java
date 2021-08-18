package com.replyglue.app.web;

import com.replyglue.app.domain.User;
import com.replyglue.app.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@ControllerAdvice
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
                registrationService.isRegisteredUser(user.getUsername()) ? new ResponseEntity(HttpStatus.CONFLICT) :
                        registrationService.isAdult(user.getDob())?
                           registrationService.registerUser(user) ? new ResponseEntity(HttpStatus.CREATED) : new ResponseEntity(HttpStatus.BAD_REQUEST)
                                : new ResponseEntity(HttpStatus.FORBIDDEN)
                : new ResponseEntity(HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleException(HttpMessageNotReadableException exception, HttpServletRequest request) {
        return new ResponseEntity("You gave an incorrect value in your registration.", HttpStatus.BAD_REQUEST);
    }

}

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
    public ResponseEntity validateUser(@RequestBody User user){
        //TODO - Cannot deserialize instance  of `java.util.Calendar

//        var responseEntity = registrationService.isUserValid(user) == true ?
//                registrationService.isRegisteredUser(user.getUsername()) ? new ResponseEntity(HttpStatus.CONFLICT) :
//                        registrationService.registerUser(user) ? new ResponseEntity(HttpStatus.CREATED) : new ResponseEntity(HttpStatus.BAD_REQUEST)
//                : new ResponseEntity(HttpStatus.BAD_REQUEST);
//        return responseEntity;
        return new ResponseEntity("Keep going", HttpStatus.CREATED);
    }

}

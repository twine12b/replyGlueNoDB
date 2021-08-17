package com.replyglue.app.controller;

import com.replyglue.app.domain.User;
import com.replyglue.app.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class RegistrationController {

    @Autowired
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping()
    public List<User> getUsersByFilter(@RequestParam("CreditCard") Optional<String> yesNoAll){
        return registrationService.findAllUsers();
    }

}

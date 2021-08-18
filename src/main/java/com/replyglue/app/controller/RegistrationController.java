package com.replyglue.app.controller;

import com.replyglue.app.domain.User;
import com.replyglue.app.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

}

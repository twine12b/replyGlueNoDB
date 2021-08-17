package com.replyglue.app.service;

import com.replyglue.app.domain.User;
import com.replyglue.app.repository.RegistrationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RegistrationService {

    @Autowired
    private final RegistrationRepository registrationRepository;

    public Optional<User> findUsersWithCreditCard(String creditCard) {
        return registrationRepository.findUserByCreditCard(creditCard);
//       return new User("r1Chard", "passWord123", "rich@me.com", null);
    }

    public List<User> findAllUsers() {
        return registrationRepository.findAllUsers();
    }
}

package com.replyglue.app.service;

import com.replyglue.app.domain.User;
import com.replyglue.app.repository.RegistrationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    public List<User> findUsersWithCreditCard(String yesNoALl) {
        switch (yesNoALl) {
            case "yes": return registrationRepository.findUsersWithCreditCard();
            case "no":  return  registrationRepository.findUsersWithOutCreditCard();
            default:    return registrationRepository.findAllUsers();
        }
    }

    public List<User> findAllUsers() {
        return registrationRepository.findAllUsers();
    }

    public User findUsersByUsername(String username) {
        return registrationRepository.findUsersByUsername(username);
    }
}

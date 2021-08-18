package com.replyglue.app.service;

import com.replyglue.app.domain.User;
import com.replyglue.app.repository.RegistrationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class RegistrationService extends ValidationService {

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


    public boolean isUserValid(User user) {
        boolean isValid;

        //TODO - refactor using combinator pattern

        isValid =
                userNameIsValid.apply(user.getUsername())
                        && passwordIsValid.apply(user.getPassword())
                        && emailIsValid.apply(user.getEmail());

        if(user.getCard() !=null){
            isValid = isValid && creditCardIsValid.apply(user.getCard());
        }

        return isValid;
    }

    public boolean isRegisteredUser(String username) {
        return registrationRepository.findUsersByUsername(username) != null;
    }

    public boolean registerUser(User newUser) {
        boolean isChecked = false;

        if(isUserValid(newUser)
                && !isRegisteredUser(newUser.getUsername()) )
        {
            registrationRepository.save(newUser);
            isChecked = true;
        }
        // TODO - check age - refactor code - use combinator pattern

        return isChecked;
    }
}

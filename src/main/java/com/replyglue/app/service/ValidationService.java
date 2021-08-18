package com.replyglue.app.service;

import com.replyglue.app.domain.User;
import com.replyglue.app.repository.RegistrationRepository;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidationService {

    private RegistrationRepository registrationRepository;

    Function<String, User> findUsersByUsername = username -> registrationRepository.findAllUsers().stream()
            .filter(user -> user.getUsername().equalsIgnoreCase(username))
            .findAny()
            .orElse(null);

    Function<String, Boolean> userNameIsValid = username -> {
        String regex = "^[a-zA-Z0-9]*{8,35}$";
        return doValidation(regex, username);
    };

    Function<String, Boolean> passwordIsValid = username -> {
        String regex = "(?=.*[A-Z])(?=.*\\d)^[a-zA-Z0-9]{8,35}$";
        return doValidation(regex, username);
    };

    Function<String, Boolean> emailIsValid = username -> {
        String regex = "^\\S+@\\S+$";   //TODO - make this expression more robust
        return doValidation(regex, username);
    };

    Function<Long, Boolean> creditCardIsValid = creditCard -> {
        String regex = "^[0-9]{16,16}$";
        return doValidation(regex, Long.toString(creditCard));
    };

    Function<String, Boolean> dobIsValid = dob -> {
        String regex = "^([0-9]{4})(-?)(1[0-2]|0[1-9])\\2(3[01]|0[1-9]|[12][0-9])$";
        return doValidation(regex, dob);
    };





    protected boolean doValidation(String regex, String stringToValidate) {
        if(stringToValidate == null) return false;

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(stringToValidate);

        return stringToValidate.length() > 0 && matcher.matches();
    }
}

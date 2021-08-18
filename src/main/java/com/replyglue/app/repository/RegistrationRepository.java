package com.replyglue.app.repository;

import com.replyglue.app.domain.User;

import java.util.List;
import java.util.Optional;

public interface RegistrationRepository {

    User findUsersByUsername(String username);

    List<User> findUsersWithCreditCard();

    List<User> findUsersWithOutCreditCard();

    User findUserByCreditCard(Long cardNumber);

    List<User>findAllUsers();

    void save(User user);
}

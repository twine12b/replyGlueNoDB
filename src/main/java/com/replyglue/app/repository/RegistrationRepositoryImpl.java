package com.replyglue.app.repository;

import com.replyglue.app.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class RegistrationRepositoryImpl { //} implements RegistrationRepository {
    @Override
    public User findUsersByUsername(String username) {
        return null;
    }

    public Optional<User> findUsersWithCreditCard(Long creditCard) {
        return Optional.of(userDAO.stream()
            .filter(user -> user.getCard().equals(creditCard))
            .findAny()
            .orElse(null)); }

    @Override
    public List<User> findUsersWithOutCreditCard() {
        return null;
    }

    @Override
    public Optional<User> findUserByCreditCard(String cardNumber) {
        return Optional.empty();
    }


    public List<User> findAllUsers() {
        return userDAO;
    }



    private List<User> userDAO = new ArrayList<User>(Arrays.asList(
            new User("r1Chard", "passWord123", "rich@me.com", null),
            new User("simonE1B", "seCret11", "rich@me.com", null),
            new User("chUckLes2", "lauGther", "rich@me.com", null, 1234123412341234l),
            new User("mEshPr0ducts", "M3SHmeUp", "rich@me.com", null),
            new User("kidAccount", "k1dPwd456", "rich@me.com", null, 1111111111111111l)
    ));
}

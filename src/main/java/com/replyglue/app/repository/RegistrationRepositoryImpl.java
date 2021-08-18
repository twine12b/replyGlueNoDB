package com.replyglue.app.repository;

import com.replyglue.app.domain.User;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class RegistrationRepositoryImpl implements RegistrationRepository {
    public RegistrationRepositoryImpl(List<User> userDAO) {
        this.userDAO = initUsers();
    }

    public User findUsersByUsername(String username) {
        return userDAO.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> findUsersWithCreditCard() {
        return userDAO.stream()
                        .filter(user -> user.getCard() != null)
                .collect(Collectors.toList()
        );
    }

    public List<User> findUsersWithOutCreditCard() {
        return userDAO.stream()
                .filter(user -> user.getCard() == null)
                .collect(Collectors.toList()
                );
    }

    public Optional<User> findUserByCreditCard(String cardNumber) {
        return Optional.empty();
    }


    public List<User> findAllUsers() {
        return userDAO;
    }

    public void save(User user) {
        userDAO.add(user);
    }

    private final List<User> userDAO;

    private List<User> initUsers(){
        return new ArrayList<>(Arrays.asList(
                new User("r1Chard", "passWord123", "rich@me.com", null),
                new User("simonE1B", "seCret11", "rich@me.com", null),
                new User("chUckLes2", "lauGther", "rich@me.com", null, 1234123412341234L),
                new User("mEshPr0ducts", "M3SHmeUp", "rich@me.com", null),
                new User("kidAccount", "k1dPwd456", "rich@me.com", null, 1111111111111111L)
        ));
    }

}

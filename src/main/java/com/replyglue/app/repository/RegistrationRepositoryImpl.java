package com.replyglue.app.repository;

import com.replyglue.app.domain.User;
import org.springframework.stereotype.Repository;

import java.sql.SQLOutput;
import java.time.LocalDate;
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

    public User findUserByCreditCard(Long cardNumber) {
        return userDAO.stream()
                .filter(user -> String.valueOf(user.getCard()).equals(String.valueOf(cardNumber)))
                .findFirst()
                .orElse(null);
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
                new User("r1Chard", "passWord123", "rich@me.com", LocalDate.of(1982,6,15)),
                new User("simonE1B", "seCret11", "rich@me.com", LocalDate.of(2010, 5,17)),
                new User("chUckLes2", "lauGther", "rich@me.com", LocalDate.of(1979,2,28), 1234123412341234L),
                new User("mEshPr0ducts", "M3SHmeUp", "rich@me.com", LocalDate.of(1999, 11,7)),
                new User("kidAccount", "teenPwd456", "rich@me.com", LocalDate.of(2005, 4,8), 1111111111111111L),
                new User("childAccount", "ch1ldPwd86", "child@me.com", LocalDate.of(2014, 9, 27)),
                new User("adultAccount", "adu1tPwd86", "adult@me.com", LocalDate.of(1985, 10, 30))
        ));
    }

}

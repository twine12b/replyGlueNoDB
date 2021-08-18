package com.replyglue.app.service;

import com.replyglue.app.domain.User;
import com.replyglue.app.repository.RegistrationRepository;
import com.replyglue.app.repository.RegistrationRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class RegistrationServiceTest {

    @Mock
    RegistrationRepository registrationRepository;

    @InjectMocks
    private RegistrationService registrationService;

    private User testUser;

    @Before
    public void setUp(){
        testUser = new User(
                "r1Chard",
                "passWord123",
                "rich@me.com",
                LocalDate.of(2008, 5, 9),
                null);

        List<User> userDAO = new ArrayList<>(Arrays.asList(
                new User("r1Chard", "passWord123", "rich@me.com", null),
                new User("simonE1B", "seCret11", "rich@me.com", null),
                new User("chUckLes2", "lauGther", "rich@me.com", null, 1234123412341234L),
                new User("mEshPr0ducts", "M3SHmeUp", "rich@me.com", null),
                new User("kidAccount", "k1dPwd456", "rich@me.com", null, 1111111111111111L)
        ));

        registrationRepository = new RegistrationRepositoryImpl(userDAO);
        registrationService = new RegistrationService(registrationRepository);
    }


    @Test
    public void testFindUsersWithCreditCards_returns_list() {
        List<User> usersWithCards = registrationService.findUsersWithCreditCard("yes");
        assertNotNull(usersWithCards);
        assertEquals(2, usersWithCards.size());
    }

    @Test
    public void testFindUsersWithNoCreditCards_returns_list() {
        List<User> usersWithNoCards = registrationService.findUsersWithCreditCard("no");
        assertNotNull(usersWithNoCards);
        assertEquals(3, usersWithNoCards.size());
    }

    @Test
    public void testFindAllUsers_returns_list() {
       List<User> usersWithNoCards = registrationService.findUsersWithCreditCard("");
        assertNotNull(usersWithNoCards);
        assertEquals(5, usersWithNoCards.size());

        usersWithNoCards = registrationService.findUsersWithCreditCard("all");
        assertNotNull(usersWithNoCards);
        assertEquals(5, usersWithNoCards.size());
    }

    @Test
    public void findUsersByUsername_shouldReturn_userInfo() {
        User user = registrationService.findUsersByUsername("r1Chard");

        assertThat(user.getUsername()).isEqualTo("r1Chard");
        assertThat(user.getPassword()).isEqualTo("passWord123");
        assertThat(user.getEmail()).isEqualTo("rich@me.com");
    }

    @Test
    public void test_isRegisteredUser_returns_boolean() {
        assertFalse(registrationService.isRegisteredUser("NoIAmNot"));
        assertTrue(registrationService.isRegisteredUser("r1Chard"));
    }

    @Test
    public void test_registerUser_returns_boolean(){
        assertFalse(registrationService.registerUser(testUser));

        testUser.setUsername("ImANewUserNotOnTheSystem123");
        assertTrue(registrationService.registerUser(testUser));
    }

}

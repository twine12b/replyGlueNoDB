package com.replyglue.app.service;

import com.replyglue.app.domain.User;
import com.replyglue.app.repository.RegistrationRepository;
import com.replyglue.app.repository.RegistrationRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class RegistrationServiceTest {

    @Mock
    RegistrationRepository registrationRepository;

    @Mock
    private RegistrationService registrationService;

    @Before
    public void setUp(){
        List<User> userDAO = new ArrayList<User>(Arrays.asList(
                new User("r1Chard", "passWord123", "rich@me.com", null),
                new User("simonE1B", "seCret11", "rich@me.com", null),
                new User("chUckLes2", "lauGther", "rich@me.com", null, 1234123412341234l),
                new User("mEshPr0ducts", "M3SHmeUp", "rich@me.com", null),
                new User("kidAccount", "k1dPwd456", "rich@me.com", null, 1111111111111111l)
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
}

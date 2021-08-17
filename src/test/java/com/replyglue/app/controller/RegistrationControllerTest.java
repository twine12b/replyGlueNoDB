package com.replyglue.app.controller;

import com.replyglue.app.domain.User;
import com.replyglue.app.service.RegistrationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RegistrationController.class)
@RunWith(SpringRunner.class)
@RequestMapping("/users")
public class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegistrationService registrationService;

    private List<User> userDAO;

    @Before
    public void setUp(){
         userDAO = new ArrayList<User>(Arrays.asList(
                new User("r1Chard", "passWord123", "rich@me.com", null),
                new User("simonE1B", "seCret11", "rich@me.com", null),
                new User("chUckLes2", "lauGther", "rich@me.com", null, 1234123412341234l),
                new User("mEshPr0ducts", "M3SHmeUp", "rich@me.com", null),
                new User("kidAccount", "k1dPwd456", "rich@me.com", null, 1111111111111111l)
        ));
    }

    @Test
    public void test_getAllUsers_shouldReturn_userList() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());

        when(registrationService.findAllUsers()).thenReturn(userDAO);
    }

    @Test
    public void test_getUsersByCreditCard_shouldReturn_Users() throws Exception {

        String creditCard = "1111111111111111";

        mockMvc.perform(get("/users")
                        .param("username", creditCard))
                .andExpect(status().isOk());

        when(registrationService.findUsersWithCreditCard(creditCard)).thenReturn(Optional.of(userDAO.get(0)));
        Optional<User> myUser = registrationService.findUsersWithCreditCard(creditCard);

        assertEquals(userDAO.get(0).getUsername(),  myUser.get().getUsername());

    }
}

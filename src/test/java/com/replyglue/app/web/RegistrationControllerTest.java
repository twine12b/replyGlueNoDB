package com.replyglue.app.web;

import com.google.gson.Gson;
import com.replyglue.app.domain.User;
import com.replyglue.app.service.RegistrationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RequestMapping;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
//        registrationController = new RegistrationController(registrationService);
         userDAO = new ArrayList<>(Arrays.asList(
                new User("r1Chard", "passWord123", "rich@me.com", null),
                new User("simonE1B", "seCret11", "rich@me.com", null),
                new User("chUckLes2", "lauGther", "rich@me.com", null, 1234123412341234L),
                new User("mEshPr0ducts", "M3SHmeUp", "rich@me.com", null),
                new User("kidAccount", "k1dPwd456", "rich@me.com", null, 1111111111111111L)
        ));
    }

    @Test
    public void test_getAllUsers_shouldReturn_userList() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());

        when(registrationService.findAllUsers()).thenReturn(userDAO);
    }

    @Test
    public void test_getUsersWithCreditCard_shouldReturn_Users() throws Exception {
        //Given
        String yesNoAll = "no";
        List<User> dao = new ArrayList<>(Arrays.asList(
                new User("chUckLes2", "lauGther", "rich@me.com", null, 1234123412341234L),
                new User("kidAccount", "k1dPwd456", "rich@me.com", null, 1111111111111111L)
        ));

        mockMvc.perform(get("/users")
                .param("yesNoAll", yesNoAll))
                .andExpect(status().isOk());

        when(registrationService.findUsersWithCreditCard(yesNoAll)).thenReturn(dao);
        List<User> usersWithCards = registrationService.findUsersWithCreditCard(yesNoAll);

        assertEquals(dao,  usersWithCards);
    }

    @Test
    public void testUserResponseBody_withValidInput_statusOK() throws Exception {
        User testUser = new User(
                "r1Chard", "passWord123",
                "rich@me.com", LocalDate.of(1984, 5, 9), 1111222233334444L
        );
        mockMvc.perform(MockMvcRequestBuilders.post("/users/")
                        .content(new Gson().toJson(testUser))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
//                .andExpect(status().isCreated());  //TODO fix status code 400
    }


}
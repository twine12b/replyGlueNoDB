package com.replyglue.app.web;

import com.google.gson.Gson;
import com.replyglue.app.domain.Payment;
import com.replyglue.app.domain.User;
import com.replyglue.app.repository.PaymentRepository;
import com.replyglue.app.repository.RegistrationRepository;
import com.replyglue.app.service.PaymentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PaymentController.class)
public class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PaymentService paymentService;

    @MockBean
    private RegistrationRepository registrationRepository;

    @MockBean
    private PaymentRepository paymentRepository;

    private User testUser;

    @Before
    public void setUp() {
        paymentService = new PaymentService(registrationRepository);
        testUser = new User(
                "r1Chard", "passWord123",
                "rich@me.com", LocalDate.of(1984, 5, 9), 1111222233334444L
        );
    }

    @Test
    public void testPaymentWhenBad_returns_statusCode_404() throws Exception {
        long paymentCardDetails = 4444111122223333L;
        int paymentAmount = 99;
        Payment payment = new Payment(paymentCardDetails, paymentAmount);

        //Act
        mockMvc.perform(MockMvcRequestBuilders.post("/payments")
                        .content(new Gson().toJson(payment))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
    }
}

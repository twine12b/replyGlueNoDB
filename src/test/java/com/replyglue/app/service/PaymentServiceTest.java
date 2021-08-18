package com.replyglue.app.service;

import com.replyglue.app.domain.Payment;
import com.replyglue.app.domain.User;
import com.replyglue.app.repository.PaymentRepository;
import com.replyglue.app.repository.RegistrationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceTest {
    @InjectMocks
    private RegistrationService registrationService;

    @Mock
    private RegistrationRepository registrationRepository;

    @Mock
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentService paymentService;

    private User testUser;

    @Before
    public void setUp() {
        paymentService = new PaymentService(registrationRepository);
        testUser = new User(
                "kidAccount",
                "k1dPwd456",
                "kid.rock@bbc.co.uk",
                LocalDate.of(2008, 5, 9),
                1111111111111111L);
    }
//return (int)Math.log(amount) == 3 && amount > 1d? true : false;
    @Test
    public void testPaymentAmount_returnsBoolean() {
        //Given
//        boolean validAmount = paymentService.isValidAmount(125.2);
//        assertFalse(validAmount);

        boolean validAmount = paymentService.isValidAmount(66);
        assertTrue(validAmount);

        validAmount = paymentService.isValidAmount(987);
        assertTrue(validAmount);

        validAmount = paymentService.isValidAmount(-500);
        assertFalse(validAmount);
    }

    @Test
    public void testCheckCardIsRegistered_returnsBoolean() {

        boolean cardExists = paymentService.verifyCardReisRegistered((1111222233334444L));
//        assertFalse(cardExists);

        cardExists = paymentService.verifyCardReisRegistered(1111111111111111L);
        assertFalse( cardExists);

//        cardExists = paymentService.checkCardExists(1234123412341234L);
//        assertTrue( cardExists);
    }

//    @Test
//    public void testMakeSuccessFullPayment_shouldReturnBoolean() {
//        //Given
//        Payment payment = new Payment(1234123412341234L, 999);
//        boolean successfulPayment = paymentService.makePayment(payment);
//
//        assertTrue(successfulPayment);
//
//    }


}

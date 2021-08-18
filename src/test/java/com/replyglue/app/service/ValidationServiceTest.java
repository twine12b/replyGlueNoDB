package com.replyglue.app.service;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidationServiceTest {
    private ValidationService validationService;

    @Before
    public void setUp() {
        validationService = new ValidationService();
    }

    @Test
    public void  test_usernameIsValid_withBadInput_shouldReturn_boolean(){
        //Given
        assertFalse(validationService.userNameIsValid.apply(null));
        assertFalse(validationService.userNameIsValid.apply(""));
        assertFalse(validationService.userNameIsValid.apply("-"));
        assertFalse(validationService.userNameIsValid.apply("9-%&"));
        assertFalse(validationService.userNameIsValid.apply("abcD123*"));
    }

    @Test
    public void test_usernameIsValid_withGoodInput_shouldReturn_boolean(){
        assertTrue(validationService.userNameIsValid.apply("Pdog91"));
        assertTrue(validationService.userNameIsValid.apply("REJojh546576LKNJV6C54S3SRHJyg54s5bb65V7b567g67"));
        assertTrue(validationService.userNameIsValid.apply("1"));
    }

    @Test
    public void test_passwordIsValid_shouldReturn_boolean(){
        assertFalse(validationService.passwordIsValid.apply(""));
        assertFalse(validationService.passwordIsValid.apply("abcdegpkjy"));
        assertFalse(validationService.passwordIsValid.apply("abcde1gpkjy"));
        assertTrue(validationService.passwordIsValid.apply("Abcde1gpkjy"));
        assertFalse(validationService.passwordIsValid.apply("1235pkjy"));
        assertFalse(validationService.passwordIsValid.apply("1235pk jy"));
        assertFalse(validationService.passwordIsValid.apply("1235pk@-%$£jy"));
    }

    @Test
    public void test_emailIsValid_shouldReturn_boolean(){
        assertFalse(validationService.emailIsValid.apply(""));
        assertFalse(validationService.emailIsValid.apply(null));
        assertTrue(validationService.emailIsValid.apply("a@b.com"));
        assertTrue(validationService.emailIsValid.apply("richard.renaud@me.com"));
    }

    @Test
    public void test_creditcardIsValid_shouldReturn_boolean() {
        assertFalse(validationService.creditCardIsValid.apply(1234L));
        assertFalse(validationService.creditCardIsValid.apply(1234567890123456890L));
        assertTrue(validationService.creditCardIsValid.apply(1234567890123456L));
    }

    @Test
    public void test_dobIsValid_shouldReturn_boolean() {
        assertFalse(validationService.dobIsValid.apply(""));
        assertFalse(validationService.dobIsValid.apply("1972-35-35"));
        assertFalse(validationService.dobIsValid.apply(null));
        assertTrue(validationService.dobIsValid.apply("1972-09-22"));
        assertFalse(validationService.dobIsValid.apply("1972-22-09"));
    }
}

package com.replyglue.app.service;

import com.replyglue.app.domain.Payment;
import com.replyglue.app.domain.User;
import com.replyglue.app.repository.PaymentRepository;
import com.replyglue.app.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService extends RegistrationService {

    @Autowired
    private PaymentRepository paymentRepository;

    public PaymentService(RegistrationRepository registrationRepository) {
        super(registrationRepository);
    }

    public boolean makePayment(Payment payment) {
        if( creditCardIsValid.apply(payment.getPayment_card()) &&
                isValidAmount(payment.getAmount())){
            if(checkCardExists(payment.getPayment_card())){
                paymentRepository.save(payment);
                paymentRepository.showPayments();
                return true;
            }
        }
        return false;
    }

    public boolean isValidAmount(Integer amount) {
        String regex = "^\\d{3}$";
        return doValidation(regex, String.valueOf(amount));
    }

    public boolean checkCardExists(Long cardNumber) {
        return findUserByCreditCard(cardNumber) != null ? true  : false;
    }

//    @Transactional
//    public Payment savePayment(Payment payment){
//        paymentRepository.save(payment);
//        return paymentRepository.findPayment(payment);
//    }

    public boolean verifyCardReisRegistered(Long cardNumber) {
        User u = findUserByCreditCard(cardNumber);
        System.out.println(u);
        return u != null? true : false;
    }
}

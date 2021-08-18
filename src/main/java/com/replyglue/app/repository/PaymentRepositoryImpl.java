package com.replyglue.app.repository;

import com.replyglue.app.domain.Payment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
@AllArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository{
    @Override
    public void save(Payment payment) {
        paymentDAO.add(payment);
    }

    @Override
    public Payment findPayment(Payment payment) {
        return null;
    }

    @Override
    public void showPayments() {
        paymentDAO.forEach(System.out::println);
    }

    private final List<Payment> paymentDAO;

    private List<Payment> initPayments(){
        return new ArrayList<>(Arrays.asList(

        ));
    }
}

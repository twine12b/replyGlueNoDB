package com.replyglue.app.repository;

import com.replyglue.app.domain.Payment;

public interface PaymentRepository {
    void save(Payment payment);

    Payment findPayment(Payment payment);

    void showPayments();

}

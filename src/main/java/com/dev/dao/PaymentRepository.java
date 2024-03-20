package com.dev.dao;

import com.dev.entity.Payment;
import org.hibernate.SessionFactory;


public class PaymentRepository extends RepositoryBase<Long, Payment> {

    public PaymentRepository(SessionFactory sessionFactory) {
        super(Payment.class, sessionFactory);
    }

}

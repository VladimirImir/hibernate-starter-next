package com.dev;

import com.dev.converter.BirthdateConverter;
import com.dev.entity.*;
import com.dev.util.HibernateUtil;
import com.dev.util.TestDataImporter;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.graph.GraphSemantic;
import org.hibernate.graph.RootGraph;
import org.hibernate.graph.SubGraph;
import org.hibernate.jdbc.Work;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Slf4j
public class HibernateRunner {

    @Transactional
    public static void main(String[] args) throws SQLException {

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            TestDataImporter.importData(sessionFactory);

//            session.setDefaultReadOnly(true);
//            session.beginTransaction();

            var profile = Profile.builder()
                    .user(session.find(User.class, 1L))
                    .language("ru")
                    .street("Kolasa 14")
                    .build();
            session.save(profile);

            var payments = session.createQuery("select p from Payment p", Payment.class)
//                    .setLockMode(LockModeType.PESSIMISTIC_FORCE_INCREMENT)
//                    .setHint("javax.persistence.lock.timeout", 5000)
                    .list();

            var payment = session.find(Payment.class, 1L);
            payment.setAmount(payment.getAmount() + 10);

//            session.getTransaction().commit();


            //session.doWork(connection -> System.out.println(connection.getTransactionIsolation()));
            /*try {
                var transaction = session.beginTransaction();

                var payment1 = session.find(Payment.class, 1L);
                var payment2 = session.find(Payment.class, 2L);

                session.getTransaction().commit();
            } catch (Exception exception) {
                session.getTransaction().rollback();
                throw exception;
            }*/
        }
    }
}
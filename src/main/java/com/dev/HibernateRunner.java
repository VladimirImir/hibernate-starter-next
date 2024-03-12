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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) throws SQLException {

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            /*var user = session.get(User.class, 1L);
            System.out.println(user.getPayments().size());
            System.out.println(user.getCompany().getName());*/
            var users = session.createQuery("select u from User u where 1 = 1", User.class)
                    .list();
            users.forEach(user -> System.out.println(user.getPayments().size()));
            users.forEach(user -> System.out.println(user.getCompany().getName()));

            session.getTransaction().commit();
        }
    }
}
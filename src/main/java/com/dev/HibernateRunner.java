package com.dev;

import com.dev.converter.BirthdateConverter;
import com.dev.entity.*;
import com.dev.util.HibernateUtil;
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

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) throws SQLException {

        Company company = Company.builder()
                .name("Amazon")
                .build();
        User user = User.builder()
                .username("ivan2@gmail.com")
                .personalInfo(PersonalInfo.builder()
                        .lastname("Ivanov")
                        .firstname("Ivan")
                        .birthDate(new Birthdate(LocalDate.of(2000, 1, 2)))
                        .build())
                .company(company)
                .build();

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            Session session1 = sessionFactory.openSession();
            try (session1) {
                Transaction transaction = session1.beginTransaction();

                //session1.save(company);
                session1.save(user);

                session1.getTransaction().commit();
            }
        }
    }
}
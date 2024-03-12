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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) throws SQLException {

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
//            session.enableFetchProfile("withCompanyAndPayment");

            var userGraph = session.createEntityGraph(User.class);
            userGraph.addAttributeNodes("company", "userChats");
            var userChatSubgraph = userGraph.addSubgraph("userChats", UserChat.class);
            userChatSubgraph.addAttributeNodes("chat");

            Map<String, Object> properties = Map.of(
                    //GraphSemantic.LOAD.getJpaHintName(), session.getEntityGraph("WithCompanyAndChat")
                    GraphSemantic.LOAD.getJpaHintName(), userGraph
            );
            var user = session.find(User.class, 1L, properties);
            System.out.println(user.getCompany().getName());
            System.out.println(user.getUserChats().size());

//            System.out.println(user.getPayments().size());
//            System.out.println(user.getCompany().getName());

            var users = session.createQuery(
                            "select u from User u " +
                            "where 1 = 1", User.class)
                    //.setHint(GraphSemantic.LOAD.getJpaHintName(), session.getEntityGraph("WithCompanyAndChat"))
                    .setHint(GraphSemantic.LOAD.getJpaHintName(), userGraph)
                    .list();
            users.forEach(it -> System.out.println(it.getUserChats().size()));
            users.forEach(it -> System.out.println(it.getCompany().getName()));

            session.getTransaction().commit();
        }
    }
}
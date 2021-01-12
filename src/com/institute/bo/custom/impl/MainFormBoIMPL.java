package com.institute.bo.custom.impl;

import com.institute.bo.custom.MainFormBO;
import com.institute.entity.Login;
import com.institute.entity.User;
import com.institute.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

public class MainFormBoIMPL implements MainFormBO {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public User checkLogin(String username, String password) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String encodedPassword = new String(Base64.getEncoder().encode(password.getBytes()));
        Query query = session.createQuery("FROM com.institute.entity.User U WHERE U.username=:a AND U.password=:p");
        query.setParameter("a", username);
        query.setParameter("p", encodedPassword);
        List<User> list = query.list();
        try {
            if (list.size() == 0) {
                return null;
            } else {
                Login login = new Login(UUID.randomUUID().toString(), LocalDateTime.now(), list.get(0));
                session.save(login);
                return list.get(0);
            }
        } finally {
            transaction.commit();
            session.close();
        }
    }
}

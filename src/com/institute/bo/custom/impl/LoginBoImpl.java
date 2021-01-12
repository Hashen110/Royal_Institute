package com.institute.bo.custom.impl;

import com.institute.bo.custom.LoginBO;
import com.institute.dto.LoginDTO;
import com.institute.entity.Login;
import com.institute.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LoginBoImpl implements LoginBO {
    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public int totalLogin() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Login> list = session.createQuery("FROM com.institute.entity.Login").list();
        transaction.commit();
        session.close();
        return list.size();
    }

    @Override
    public int todayLogin() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        LocalDate now = LocalDate.now();
        NativeQuery query = session.createNativeQuery("SELECT * FROM Login WHERE date_login LIKE '" + now.toString() + "%'");
        List<Login> list = query.list();
        transaction.commit();
        session.close();
        return list.size();
    }

    @Override
    public int yesterdayLogin() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        LocalDate now = LocalDate.now().minusDays(1);
        NativeQuery query = session.createNativeQuery("SELECT * FROM Login WHERE date_login LIKE '" + now.toString() + "%'");
        List<Login> list = query.list();
        transaction.commit();
        session.close();
        return list.size();
    }

    @Override
    public int thisMonthLogin() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        LocalDate now = LocalDate.now();
        NativeQuery query = session.createNativeQuery("SELECT * FROM Login WHERE date_login LIKE '" + now.toString().substring(0, 7) + "%'");
        List<Login> list = query.list();
        transaction.commit();
        session.close();
        return list.size();
    }

    @Override
    public ArrayList<LoginDTO> getTotalLogin() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Login> list = session.createQuery("FROM com.institute.entity.Login").list();
        transaction.commit();
        session.close();
        return returnLogins(list);
    }

    @Override
    public ArrayList<LoginDTO> getTodayLogin() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        LocalDate now = LocalDate.now();
        NativeQuery query = session.createNativeQuery("SELECT * FROM Login WHERE date_login LIKE '" + now.toString() + "%'", Login.class);
        List<Login> list = query.list();
        transaction.commit();
        session.close();
        return returnLogins(list);
    }

    @Override
    public ArrayList<LoginDTO> getYesterdayLogin() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        LocalDate now = LocalDate.now().minusDays(1);
        NativeQuery query = session.createNativeQuery("SELECT * FROM Login WHERE date_login LIKE '" + now.toString() + "%'", Login.class);
        List<Login> list = query.list();
        transaction.commit();
        session.close();
        return returnLogins(list);
    }

    @Override
    public ArrayList<LoginDTO> getThisMonthLogin() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        LocalDate now = LocalDate.now();
        NativeQuery query = session.createNativeQuery("SELECT * FROM Login WHERE date_login LIKE '" + now.toString().substring(0, 7) + "%'", Login.class);
        List<Login> list = query.list();
        transaction.commit();
        session.close();
        return returnLogins(list);
    }

    @Override
    public ArrayList<LoginDTO> getPastMonthLogin() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        LocalDate now = LocalDate.now().minusMonths(1);
        NativeQuery query = session.createNativeQuery("SELECT * FROM Login WHERE date_login LIKE '" + now.toString().substring(0, 7) + "%'", Login.class);
        List<Login> list = query.list();
        transaction.commit();
        session.close();
        return returnLogins(list);
    }

    private ArrayList<LoginDTO> returnLogins(List<Login> list) {
        if (list.size() == 0 | list == null) {
            return null;
        } else {
            ArrayList<LoginDTO> logins = new ArrayList<>();
            for (Login login : list) {
                logins.add(new LoginDTO(login.getId(), login.getDate_login().toString(), login.getUser().getUsername()));
            }
            return logins;
        }
    }
}

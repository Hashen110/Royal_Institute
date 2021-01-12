package com.institute.bo.custom.impl;

import com.institute.bo.custom.DashboardBO;
import com.institute.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class DashboardBOBoIMPL implements DashboardBO {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public int getTotalStudents() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT count (S.id) FROM com.institute.entity.Student S");
        List list = query.list();
        transaction.commit();
        session.close();
        return Integer.parseInt(list.get(0).toString());
    }

    @Override
    public int getTotalCourses() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT count (C.id) FROM com.institute.entity.Course C");
        List list = query.list();
        transaction.commit();
        session.close();
        return Integer.parseInt(list.get(0).toString());
    }

    @Override
    public int getTotalPayments() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT count (P.id) FROM com.institute.entity.Payment P");
        List list = query.list();
        transaction.commit();
        session.close();
        return Integer.parseInt(list.get(0).toString());
    }

    @Override
    public int getTotalUsers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT count (U.id) FROM com.institute.entity.User U");
        List list = query.list();
        transaction.commit();
        session.close();
        return Integer.parseInt(list.get(0).toString());
    }
}

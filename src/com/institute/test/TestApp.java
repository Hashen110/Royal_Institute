package com.institute.test;

import com.institute.entity.Course;
import com.institute.entity.Payment;
import com.institute.entity.Registration;
import com.institute.entity.Student;
import com.institute.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class TestApp {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

//        User user = new User(UUID.randomUUID().toString(), "Hashen Abeysekara", "Hashen", Base64.getEncoder().encodeToString("password".getBytes()), LocalDateTime.now());
//        Login login = new Login(UUID.randomUUID().toString(), LocalDateTime.now(), user);
//
//        session.save(user);
//        session.save(login);

//        Course course = new Course("CID0001", "Motor Mechanics", "Type 1", "1 year", 100000.00);
//        Course course1 = new Course("CID0002", "Quantity Surveying", "Type 2", "1 year", 100000.00);
//        Course course2 = new Course("CID0003", "Electronics", "Type 3", "2 years", 150000.00);
//        Course course3 = new Course("CID0004", "Foreign Languages - English", "Type 4", "6 months", 50000.00);
//        Course course4 = new Course("CID0005", "Computer Hardware", "Type 5", "6 months", 50000.00);
//
//        session.save(course);
//        session.save(course1);
//        session.save(course2);
//        session.save(course3);
//        session.save(course4);

//        Student student = new Student("SID0003", "Danapala", "Kandy", "+94779090689", LocalDate.of(2000, 10, 20), "Male", LocalDateTime.now());
//        Payment payment = new Payment(UUID.randomUUID().toString(), LocalDateTime.now(), 110000.00, 0.00, 110000.00);
//        Registration registration = new Registration("RID0003", LocalDate.now(), 10000.00, payment, student, course1);

//        session.save(registration);
//
//        session.save(student);
//        session.save(payment);
//        session.save(registration);


        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}

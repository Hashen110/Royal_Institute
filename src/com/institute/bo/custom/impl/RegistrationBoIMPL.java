package com.institute.bo.custom.impl;

import com.institute.bo.custom.RegistrationBO;
import com.institute.dto.CourseDTO;
import com.institute.dto.PaymentDTO;
import com.institute.dto.RegistrationDTO;
import com.institute.dto.StudentDTO;
import com.institute.entity.Course;
import com.institute.entity.Payment;
import com.institute.entity.Registration;
import com.institute.entity.Student;
import com.institute.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegistrationBoIMPL implements RegistrationBO {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public int getTotalRegistrations() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List list = session.createQuery("SELECT count (R.id)FROM com.institute.entity.Registration R").list();
        transaction.commit();
        session.close();
        return Integer.parseInt(list.get(0).toString());
    }

    @Override
    public int getTodayRegistrations() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        LocalDate localDate = LocalDate.now();
        List list = session.createQuery("SELECT count (R.id)FROM com.institute.entity.Registration R WHERE R.regDate LIKE '" + localDate + "%'").list();
        transaction.commit();
        session.close();
        return Integer.parseInt(list.get(0).toString());
    }

    @Override
    public int getYesterdayRegistrations() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        LocalDate localDate = LocalDate.now().minusDays(1);
        List list = session.createQuery("SELECT count (R.id)FROM com.institute.entity.Registration R WHERE R.regDate LIKE '" + localDate + "%'").list();
        transaction.commit();
        session.close();
        return Integer.parseInt(list.get(0).toString());
    }

    @Override
    public ArrayList<RegistrationDTO> getRegistrations() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Registration> list = session.createQuery("FROM com.institute.entity.Registration").list();
        transaction.commit();
        session.close();
        if (list == null) return null;
        if (list.size() == 0) return null;
        ArrayList<RegistrationDTO> registrations = new ArrayList<>();
        for (Registration registration : list) {
            registrations.add(new RegistrationDTO(registration.getId(), registration.getRegDate().toString(), registration.getRegFee(),
                    registration.getPayment().getId(), registration.getStudent().getName(), registration.getCourse().getName()));
        }
        return registrations;
    }

    @Override
    public String getNewId() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT R.id FROM com.institute.entity.Registration R ORDER BY R.id DESC").setMaxResults(1);
        List list = query.list();
        transaction.commit();
        session.close();
        if (list.size() == 0) {
            return "RID0001";
        } else {
            int lastId = Integer.parseInt(list.get(0).toString().substring(3));
            lastId++;
            if (lastId < 10) {
                return "RID000" + lastId;
            } else if (lastId < 100) {
                return "RID00" + lastId;
            } else if (lastId < 1000) {
                return "RID0" + lastId;
            } else {
                return "RID" + lastId;
            }
        }
    }

    @Override
    public ArrayList<CourseDTO> getCourses() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM com.institute.entity.Course");
        List<Course> list = query.list();
        transaction.commit();
        session.close();
        ArrayList<CourseDTO> courses = new ArrayList<>();
        for (Course course : list) {
            courses.add(new CourseDTO(course.getId(), course.getName(), course.getType(), course.getDuration(), course.getPrice()));
        }
        return courses;
    }

    @Override
    public ArrayList<StudentDTO> getStudents() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM com.institute.entity.Student");
        List<Student> list = query.list();
        transaction.commit();
        session.close();
        ArrayList<StudentDTO> students = new ArrayList<>();
        for (Student student : list) {
            students.add(new StudentDTO(student.getId(), student.getName(), student.getAddress(), student.getContact(), student.getBirthday().toString(),
                    student.getGender(), student.getDate_joined().toString()));
        }
        return students;
    }

    @Override
    public boolean addRegistration(String studentId, String courseId, RegistrationDTO registrationDTO, PaymentDTO paymentDTO) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Student student = session.get(Student.class, studentId);
            Course course = session.get(Course.class, courseId);
            Payment payment = new Payment(paymentDTO.getId(), paymentDTO.getDate(), paymentDTO.getAmount(), paymentDTO.getDiscount(), paymentDTO.getTotal());
            Registration registration = new Registration(registrationDTO.getId(), LocalDate.parse(registrationDTO.getDate()), registrationDTO.getFee(),
                    payment, student, course);
            session.save(registration);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String getNewPaymentId() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT P.id FROM com.institute.entity.Payment P ORDER BY P.id DESC").setMaxResults(1);
        List list = query.list();
        transaction.commit();
        session.close();
        if (list.size() == 0) {
            return "PID0001";
        } else {
            int lastId = Integer.parseInt(list.get(0).toString().substring(3));
            lastId++;
            if (lastId < 10) {
                return "PID000" + lastId;
            } else if (lastId < 100) {
                return "PID00" + lastId;
            } else if (lastId < 1000) {
                return "PID0" + lastId;
            } else {
                return "PID" + lastId;
            }
        }
    }

}

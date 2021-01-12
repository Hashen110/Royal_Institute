package com.institute.bo.custom.impl;

import com.institute.bo.custom.StudentBO;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StudentBoIMPL implements StudentBO {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public ArrayList<StudentDTO> getAllStudent() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Student> list = session.createQuery("FROM com.institute.entity.Student").list();
        transaction.commit();
        session.close();
        return returnStudents(list);
    }

    @Override
    public ArrayList<StudentDTO> getMaleStudent() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Student> list = session.createQuery("FROM com.institute.entity.Student S WHERE S.gender='Male'").list();
        transaction.commit();
        session.close();
        return returnStudents(list);
    }

    @Override
    public ArrayList<StudentDTO> getFemaleStudent() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Student> list = session.createQuery("FROM com.institute.entity.Student S WHERE S.gender='Female'").list();
        transaction.commit();
        session.close();
        return returnStudents(list);
    }

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
    public int getTotalMaleStudents() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT count (S.id) FROM com.institute.entity.Student S WHERE S.gender=:g");
        query.setParameter("g", "Male");
        List list = query.list();
        transaction.commit();
        session.close();
        return Integer.parseInt(list.get(0).toString());
    }

    @Override
    public int getTotalFemaleStudents() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT count (S.id) FROM com.institute.entity.Student S WHERE S.gender=:g");
        query.setParameter("g", "Female");
        List list = query.list();
        transaction.commit();
        session.close();
        return Integer.parseInt(list.get(0).toString());
    }

    @Override
    public String getNewId() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT S.id FROM com.institute.entity.Student S ORDER BY S.id DESC").setMaxResults(1);
        List list = query.list();
        transaction.commit();
        session.close();
        if (list.size() == 0) {
            return "SID0001";
        } else {
            int lastId = Integer.parseInt(list.get(0).toString().substring(3));
            lastId++;
            if (lastId < 10) {
                return "SID000" + lastId;
            } else if (lastId < 100) {
                return "SID00" + lastId;
            } else if (lastId < 1000) {
                return "SID0" + lastId;
            } else {
                return "SID" + lastId;
            }
        }
    }

    @Override
    public String getNewRegId() {
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
    public boolean addStudent(StudentDTO studentDTO, RegistrationDTO registrationDTO, PaymentDTO paymentDTO, String courseId) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Course course = session.get(Course.class, courseId);
            Student student = new Student(studentDTO.getId(), studentDTO.getName(), studentDTO.getAddress(), studentDTO.getContact(),
                    LocalDate.parse(studentDTO.getBirthday()), studentDTO.getGender(), LocalDateTime.parse(studentDTO.getDate_joined()));
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
    public StudentDTO getStudent(String id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Student student = session.get(Student.class, id);
        transaction.commit();
        session.close();
        if (student == null) return null;
        return new StudentDTO(student.getId(), student.getName(), student.getAddress(), student.getContact(),
                student.getBirthday().toString(), student.getGender(), student.getDate_joined().toString());
    }

    @Override
    public boolean editStudent(StudentDTO studentDTO) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Student student = session.get(Student.class, studentDTO.getId());
            student.setName(studentDTO.getName());
            student.setAddress(studentDTO.getAddress());
            student.setContact(studentDTO.getContact());
            student.setBirthday(LocalDate.parse(studentDTO.getBirthday()));
            student.setGender(studentDTO.getGender());
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<CourseDTO> getStudentCourses(String studentId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Registration> list = session.createNativeQuery("SELECT * FROM Registration WHERE studentId='" + studentId + "'", Registration.class).list();
        transaction.commit();
        session.close();
        if (list.size() == 0) return null;
        ArrayList<CourseDTO> courses = new ArrayList<>();
        for (Registration registration : list) {
            courses.add(new CourseDTO(registration.getCourse().getId(), registration.getCourse().getName(), registration.getCourse().getType(),
                    registration.getCourse().getDuration(), registration.getCourse().getPrice()));
        }
        return courses;
    }

    private ArrayList<StudentDTO> returnStudents(List<Student> list) {
        if (list.size() == 0 | list == null) {
            return null;
        } else {
            ArrayList<StudentDTO> students = new ArrayList<>();
            for (Student student : list) {
                students.add(new StudentDTO(student.getId(), student.getName(), student.getAddress(), student.getContact(),
                        student.getBirthday().toString(), student.getGender(), student.getDate_joined().toString()));
            }
            return students;
        }
    }
}

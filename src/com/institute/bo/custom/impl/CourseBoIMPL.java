package com.institute.bo.custom.impl;

import com.institute.bo.custom.CourseBO;
import com.institute.dto.CourseDTO;
import com.institute.entity.Course;
import com.institute.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CourseBoIMPL implements CourseBO {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public int getCourseCount() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List list = session.createQuery("SELECT count (C.id) FROM com.institute.entity.Course C").list();
        transaction.commit();
        session.close();
        return Integer.parseInt(list.get(0).toString());
    }

    @Override
    public ArrayList<CourseDTO> getCourses() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Course> list = session.createQuery("FROM com.institute.entity.Course").list();
        transaction.commit();
        session.close();
        ArrayList<CourseDTO> courses = new ArrayList<>();
        for (Course course : list) {
            courses.add(new CourseDTO(course.getId(), course.getName(), course.getType(), course.getDuration(), course.getPrice()));
        }
        return courses;
    }

    @Override
    public String getNewCourseId() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT C.id FROM com.institute.entity.Course C ORDER BY C.id DESC").setMaxResults(1);
        List list = query.list();
        transaction.commit();
        session.close();
        if (list.size() == 0) {
            return "CID0001";
        } else {
            int lastId = Integer.parseInt(list.get(0).toString().substring(3));
            lastId++;
            if (lastId < 10) {
                return "CID000" + lastId;
            } else if (lastId < 100) {
                return "CID00" + lastId;
            } else if (lastId < 1000) {
                return "CID0" + lastId;
            } else {
                return "CID" + lastId;
            }
        }
    }

    @Override
    public boolean addCourse(CourseDTO courseDTO) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Course course = new Course(courseDTO.getId(), courseDTO.getName(), courseDTO.getType(), courseDTO.getDuration(), courseDTO.getPrice());
            session.save(course);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean editCourse(CourseDTO courseDTO) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Course course = session.get(Course.class, courseDTO.getId());
            course.setName(courseDTO.getName());
            course.setType(courseDTO.getType());
            course.setDuration(courseDTO.getDuration());
            course.setPrice(courseDTO.getPrice());
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public CourseDTO getCourse(String id) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Course course = session.get(Course.class, id);
            transaction.commit();
            session.close();
            if (course == null) return null;
            return new CourseDTO(course.getId(), course.getName(), course.getType(), course.getDuration(), course.getPrice());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

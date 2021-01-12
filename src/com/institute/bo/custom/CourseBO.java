package com.institute.bo.custom;

import com.institute.bo.SuperBO;
import com.institute.dto.CourseDTO;

import java.util.ArrayList;

public interface CourseBO extends SuperBO {
    int getCourseCount();

    ArrayList<CourseDTO> getCourses();

    String getNewCourseId();

    boolean addCourse(CourseDTO courseDTO);

    boolean editCourse(CourseDTO courseDTO);

    CourseDTO getCourse(String id);
}

package com.institute.bo.custom;

import com.institute.bo.SuperBO;
import com.institute.dto.CourseDTO;
import com.institute.dto.PaymentDTO;
import com.institute.dto.RegistrationDTO;
import com.institute.dto.StudentDTO;

import java.util.ArrayList;

public interface StudentBO extends SuperBO {
    ArrayList<StudentDTO> getAllStudent();

    ArrayList<StudentDTO> getMaleStudent();

    ArrayList<StudentDTO> getFemaleStudent();

    int getTotalStudents();

    int getTotalMaleStudents();

    int getTotalFemaleStudents();

    String getNewId();

    String getNewRegId();

    String getNewPaymentId();

    ArrayList<CourseDTO> getCourses();

    boolean addStudent(StudentDTO studentDTO, RegistrationDTO registrationDTO, PaymentDTO paymentDTO, String courseId);

    StudentDTO getStudent(String id);

    boolean editStudent(StudentDTO studentDTO);

    ArrayList<CourseDTO> getStudentCourses(String studentId);
}

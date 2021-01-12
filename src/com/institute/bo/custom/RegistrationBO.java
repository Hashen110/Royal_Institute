package com.institute.bo.custom;

import com.institute.bo.SuperBO;
import com.institute.dto.CourseDTO;
import com.institute.dto.PaymentDTO;
import com.institute.dto.RegistrationDTO;
import com.institute.dto.StudentDTO;

import java.util.ArrayList;

public interface RegistrationBO extends SuperBO {
    int getTotalRegistrations();

    int getTodayRegistrations();

    int getYesterdayRegistrations();

    ArrayList<RegistrationDTO> getRegistrations();

    String getNewId();

    ArrayList<CourseDTO> getCourses();

    ArrayList<StudentDTO> getStudents();

    boolean addRegistration(String studentId, String courseId, RegistrationDTO registrationDTO, PaymentDTO paymentDTO);

    String getNewPaymentId();
}

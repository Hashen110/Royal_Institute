package com.institute.bo.custom;

import com.institute.bo.SuperBO;

public interface DashboardBO extends SuperBO {
    int getTotalStudents();

    int getTotalCourses();

    int getTotalPayments();

    int getTotalUsers();
}

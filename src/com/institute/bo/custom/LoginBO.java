package com.institute.bo.custom;

import com.institute.bo.SuperBO;
import com.institute.dto.LoginDTO;

import java.util.ArrayList;

public interface LoginBO extends SuperBO {
    int totalLogin();

    int todayLogin();

    int yesterdayLogin();

    int thisMonthLogin();

    ArrayList<LoginDTO> getTotalLogin();

    ArrayList<LoginDTO> getTodayLogin();

    ArrayList<LoginDTO> getYesterdayLogin();

    ArrayList<LoginDTO> getThisMonthLogin();

    ArrayList<LoginDTO> getPastMonthLogin();
}

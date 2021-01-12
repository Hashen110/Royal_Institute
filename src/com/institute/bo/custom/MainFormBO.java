package com.institute.bo.custom;

import com.institute.bo.SuperBO;
import com.institute.entity.User;

public interface MainFormBO extends SuperBO {
    User checkLogin(String username, String password);
}

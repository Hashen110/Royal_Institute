package com.institute.bo;

import com.institute.bo.custom.impl.*;

public class BoFactory {
    private BoFactory() {

    }

    public static <T> T getBo(BoType boType) {
        switch (boType) {
            case MAINFORM:
                return (T) new MainFormBoIMPL();
            case LOGIN:
                return (T) new LoginBoImpl();
            case DASHBOARD:
                return (T) new DashboardBOBoIMPL();
            case STUDENT:
                return (T) new StudentBoIMPL();
            case COURSE:
                return (T) new CourseBoIMPL();
            case REGISTRATION:
                return (T) new RegistrationBoIMPL();
            default:
                return null;
        }
    }
}

package com.institute.test;

import com.institute.bo.BoFactory;
import com.institute.bo.BoType;
import com.institute.bo.custom.StudentBO;
import com.institute.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Base64;
import java.util.UUID;

public class Test {
    public static void main(String[] args) {
//        UUID uuid = UUID.randomUUID();
//        System.out.println(uuid);
//        Base64.Encoder encoder = Base64.getEncoder();
//        byte[] encode = encoder.encode("Hashen".getBytes());
//        String s = new String(encode);
//        System.out.println(s);
//        Base64.Decoder decoder = Base64.getDecoder();
//        byte[] decode = decoder.decode(s);
//        String s1 = new String(decode);
//        System.out.println(s1);

        StudentBO studentBO = BoFactory.getBo(BoType.STUDENT);
//        String newId = studentBO.getNewId();
//        System.out.println(newId);

//        System.out.println(Integer.parseInt("SID0001".substring(3)));

//        ArrayList<String> courseNames = studentBO.getCourseNames();
//        for (int i = 0; i < courseNames.size(); i++) {
//            System.out.println(courseNames.get(i));
//        }
    }
}

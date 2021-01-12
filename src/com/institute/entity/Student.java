package com.institute.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {
    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String contact;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private LocalDateTime date_joined;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Registration> registrations = new ArrayList<>();

    public Student() {
    }

    public Student(String id, String name, String address, String contact, LocalDate birthday, String gender, LocalDateTime date_joined) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.birthday = birthday;
        this.gender = gender;
        this.date_joined = date_joined;
    }

    public Student(String id, String name, String address, String contact, LocalDate birthday, String gender, LocalDateTime date_joined, List<Registration> registrations) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.birthday = birthday;
        this.gender = gender;
        this.date_joined = date_joined;
        this.registrations = registrations;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDateTime getDate_joined() {
        return date_joined;
    }

    public void setDate_joined(LocalDateTime date_joined) {
        this.date_joined = date_joined;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                ", date_joined=" + date_joined +
                '}';
    }
}

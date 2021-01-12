package com.institute.dto;

public class StudentDTO {
    private String id;
    private String name;
    private String address;
    private String contact;
    private String birthday;
    private String gender;
    private String date_joined;

    public StudentDTO() {
    }

    public StudentDTO(String id, String name, String address, String contact, String birthday, String gender, String date_joined) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.birthday = birthday;
        this.gender = gender;
        this.date_joined = date_joined;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDate_joined() {
        return date_joined;
    }

    public void setDate_joined(String date_joined) {
        this.date_joined = date_joined;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", birthday='" + birthday + '\'' +
                ", gender='" + gender + '\'' +
                ", date_joined='" + date_joined + '\'' +
                '}';
    }
}

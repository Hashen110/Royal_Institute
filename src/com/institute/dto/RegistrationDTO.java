package com.institute.dto;

public class RegistrationDTO {
    private String id;
    private String date;
    private double fee;
    private String payment;
    private String student;
    private String course;

    public RegistrationDTO() {
    }

    public RegistrationDTO(String id, String date, double fee, String payment, String student, String course) {
        this.id = id;
        this.date = date;
        this.fee = fee;
        this.payment = payment;
        this.student = student;
        this.course = course;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "RegistrationDTO{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", fee=" + fee +
                ", payment='" + payment + '\'' +
                ", student='" + student + '\'' +
                ", course='" + course + '\'' +
                '}';
    }
}

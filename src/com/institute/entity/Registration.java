package com.institute.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Registration {
    @Id
    private String id;

    @Column(nullable = false)
    private LocalDate regDate;

    @Column(nullable = false)
    private Double regFee;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "paymentId", referencedColumnName = "id", nullable = false)
    private Payment payment;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "studentId", referencedColumnName = "id", nullable = false)
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "courseId", referencedColumnName = "id", nullable = false)
    private Course course;

    public Registration() {
    }

    public Registration(String id, LocalDate regDate, Double regFee) {
        this.id = id;
        this.regDate = regDate;
        this.regFee = regFee;
    }

    public Registration(String id, LocalDate regDate, Double regFee, Payment payment, Student student, Course course) {
        this.id = id;
        this.regDate = regDate;
        this.regFee = regFee;
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

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public Double getRegFee() {
        return regFee;
    }

    public void setRegFee(Double regFee) {
        this.regFee = regFee;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "id='" + id + '\'' +
                ", regDate=" + regDate +
                ", regFee=" + regFee +
                '}';
    }
}

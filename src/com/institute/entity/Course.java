package com.institute.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {
    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String duration;

    @Column(nullable = false)
    private Double price;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Registration> registrations = new ArrayList<>();

    public Course() {
    }

    public Course(String id, String name, String type, String duration, Double price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.duration = duration;
        this.price = price;
    }

    public Course(String id, String name, String type, String duration, Double price, List<Registration> registrations) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.duration = duration;
        this.price = price;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", duration='" + duration + '\'' +
                ", price=" + price +
                '}';
    }
}

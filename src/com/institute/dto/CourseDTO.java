package com.institute.dto;

public class CourseDTO {
    private String id;
    private String name;
    private String type;
    private String duration;
    private double price;

    public CourseDTO(String id, String name, String type, String duration, double price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.duration = duration;
        this.price = price;
    }

    public CourseDTO() {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", duration='" + duration + '\'' +
                ", price=" + price +
                '}';
    }
}

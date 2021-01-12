package com.institute.dto;

import java.time.LocalDateTime;

public class PaymentDTO {
    private String id;
    private LocalDateTime date;
    private double amount;
    private double discount;
    private double total;

    public PaymentDTO() {
    }

    public PaymentDTO(String id, LocalDateTime date, double amount, double discount, double total) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.discount = discount;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                ", discount=" + discount +
                ", total=" + total +
                '}';
    }
}

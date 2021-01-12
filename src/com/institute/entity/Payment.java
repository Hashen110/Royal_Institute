package com.institute.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Payment {
    @Id
    private String id;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private Double amount;

    private Double discount;

    @Column(nullable = false)
    private Double total;

    @OneToOne(mappedBy = "payment", cascade = CascadeType.ALL)
    private Registration registration;

    public Payment() {
    }

    public Payment(String id, LocalDateTime date, Double amount, Double discount, Double total) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.discount = discount;
        this.total = total;
    }

    public Payment(String id, LocalDateTime date, Double amount, Double discount, Double total, Registration registration) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.discount = discount;
        this.total = total;
        this.registration = registration;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                ", discount=" + discount +
                ", total=" + total +
                '}';
    }
}

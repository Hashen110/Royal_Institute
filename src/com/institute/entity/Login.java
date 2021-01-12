package com.institute.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Login {
    @Id
    private String id;

    @Column(nullable = false, updatable = false)
    private LocalDateTime date_login;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false, updatable = false, referencedColumnName = "id")
    private User user;

    public Login() {
    }

    public Login(String id, LocalDateTime date_login, User user) {
        this.id = id;
        this.date_login = date_login;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDate_login() {
        return date_login;
    }

    public void setDate_login(LocalDateTime date_login) {
        this.date_login = date_login;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                ", date_login=" + date_login +
                '}';
    }
}

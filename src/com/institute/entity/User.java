package com.institute.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Entity
public class User {
    @Id
    private String id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, updatable = false)
    private LocalDateTime date_joined;

    @OneToMany(mappedBy = "user")
    private List<Login> logins = new ArrayList<>();

    public User() {
    }

    public User(String id, String name, String username, String password, LocalDateTime date_joined) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getDate_joined() {
        return date_joined;
    }

    public void setDate_joined(LocalDateTime date_joined) {
        this.date_joined = date_joined;
    }

    public List<Login> getLogins() {
        return logins;
    }

    public void setLogins(List<Login> logins) {
        this.logins = logins;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", date_joined=" + date_joined +
                '}';
    }
}

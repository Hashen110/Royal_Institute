package com.institute.dto;

public class LoginDTO {
    private String id;
    private String date_login;
    private String username;

    public LoginDTO() {
    }

    public LoginDTO(String id, String date_login, String username) {
        this.id = id;
        this.date_login = date_login;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate_login() {
        return date_login;
    }

    public void setDate_login(String date_login) {
        this.date_login = date_login;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "id='" + id + '\'' +
                ", date_login='" + date_login + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}

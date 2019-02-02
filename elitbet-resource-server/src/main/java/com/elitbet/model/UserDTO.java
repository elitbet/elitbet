package com.elitbet.model;

import com.elitbet.entities.User;

public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String language;

    public UserDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", language='" + language + '\'' +
                '}';
    }

    public User toEntity() {
        User user = new User();
        user.setUsername(username);
        user.setActive(true);
        user.setPassword(password);
        return user;
    }
}

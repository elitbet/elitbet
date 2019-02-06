package com.elitbet.wagers.model.dto;

import com.elitbet.wagers.model.entities.User;

public class UserPublicDTO {
    private long id;
    private String username;
    private String language;
    private String surname;

    public UserPublicDTO() {
    }

    public UserPublicDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.language = user.getLanguage();
        this.surname = user.getSurname();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    public User toEntity(){
        User user = new User();
        user.setId(id);
        user.setSurname(surname);
        user.setLanguage(language);
        user.setUsername(username);
        return user;
    }

}

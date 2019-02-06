package com.elitbet.wagers.model.dto;


import com.elitbet.wagers.model.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public class UserDTO extends UserPublicDTO {

    private String password;

    public UserDTO() {
    }

    public UserDTO(User user) {
        super(user);
        this.password = user.getPassword();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User toEntity() {
        User user = super.toEntity();
        user.setPassword(password);
        return user;
    }

}

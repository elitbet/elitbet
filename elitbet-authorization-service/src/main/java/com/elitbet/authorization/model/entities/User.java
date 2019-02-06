package com.elitbet.authorization.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity(name = "USER")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @Column(name="USER_NAME")
    private String username;

    @Column(name="PASSWORD")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLES", joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID"))
    private List<Role> roles;

    @Column(name="ENABLED")
    private boolean active;

    @Column(name="ACCOUNT_LOCKED")
    private boolean accountLocked;

    @Column(name="CREDENTIALS_EXPIRED")
    private boolean credentialsExprired;

    @Column(name="ACCOUNT_EXPIRED")
    private boolean accountExprired;

    @Column(name="USERLANGUAGE")
    private String language;

    @Column(name="USERSURNAME")
    private String surname;

    public User() {
    }

    public User(String username, String password, List<Role> roles, boolean active, boolean accountLocked, boolean credentialsExprired, boolean accountExprired, String language, String surname) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.active = active;
        this.accountLocked = accountLocked;
        this.credentialsExprired = credentialsExprired;
        this.accountExprired = accountExprired;
        this.language = language;
        this.surname = surname;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public boolean isCredentialsExprired() {
        return credentialsExprired;
    }

    public void setCredentialsExprired(boolean credentialsExprired) {
        this.credentialsExprired = credentialsExprired;
    }

    public boolean isAccountExprired() {
        return accountExprired;
    }

    public void setAccountExprired(boolean accountExprired) {
        this.accountExprired = accountExprired;
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
}

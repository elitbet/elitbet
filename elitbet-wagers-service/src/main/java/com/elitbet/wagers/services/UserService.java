package com.elitbet.wagers.services;

import com.elitbet.wagers.exceptions.SuchUserAlreadyExistException;
import com.elitbet.wagers.model.entities.Role;
import com.elitbet.wagers.model.entities.User;
import com.elitbet.wagers.repositories.RoleRepository;
import com.elitbet.wagers.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(()->new NoSuchElementException("No such user exists"));
    }
}

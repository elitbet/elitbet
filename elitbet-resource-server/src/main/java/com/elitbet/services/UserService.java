package com.elitbet.services;

import com.elitbet.entities.User;
import com.elitbet.repositories.RoleRepository;
import com.elitbet.entities.Role;
import com.elitbet.exceptions.SuchUserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.elitbet.repositories.UserRepository;

import java.util.Arrays;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(User user) {
        if(userRepository.existsByUsername(user.getUsername())){
            throw new SuchUserAlreadyExistException("Such user already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findById(1L).get();
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }
}

package com.elitbet.controller;

import com.elitbet.repositories.RoleRepository;
import com.elitbet.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;


@RestController
public class HomeController {
    private final static Logger logger = Logger.getLogger(HomeController.class.getName());

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @GetMapping(value = "/")
    public String index(){
        return "Hello world";
    }

    @GetMapping(value = "/private")
    @PreAuthorize("#oauth2.hasScope('write')")
    public String privateArea(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        StringBuilder sb = new StringBuilder();
        userDetails.getAuthorities().forEach(grantedAuthority -> {sb.append(grantedAuthority.getAuthority() + " ");});
        return sb.toString();
    }

}

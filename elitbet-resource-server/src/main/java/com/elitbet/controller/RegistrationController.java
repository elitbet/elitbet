package com.elitbet.controller;

import com.elitbet.entities.User;
import com.elitbet.model.UserDTO;
import com.elitbet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {

    @Autowired
    UserService userService;


    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody UserDTO userDTO){
        User user = userDTO.toEntity();
        userService.register(user);
    }
}

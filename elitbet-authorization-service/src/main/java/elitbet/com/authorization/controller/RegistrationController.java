package elitbet.com.authorization.controller;

import elitbet.com.authorization.model.entities.User;
import elitbet.com.authorization.model.dto.UserDTO;
import elitbet.com.authorization.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.TreeMap;

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

    @RequestMapping(value = "/help", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Map<String,String> help(){
        Map<String,String> helper = new TreeMap<>();
        helper.put("/register","New user registration");
        helper.put("/oauth/token","Get token");
        return helper;
    }
}

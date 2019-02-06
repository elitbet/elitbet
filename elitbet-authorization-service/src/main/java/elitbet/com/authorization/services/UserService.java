package elitbet.com.authorization.services;

import elitbet.com.authorization.exceptions.SuchUserAlreadyExistException;
import elitbet.com.authorization.repositories.RoleRepository;
import elitbet.com.authorization.model.entities.User;
import elitbet.com.authorization.model.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import elitbet.com.authorization.repositories.UserRepository;

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
        user.setActive(true);
        user.setAccountExprired(false);
        user.setAccountLocked(false);
        user.setCredentialsExprired(false);
        userRepository.save(user);
    }
}

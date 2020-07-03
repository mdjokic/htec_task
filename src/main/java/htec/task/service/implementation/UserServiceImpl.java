package htec.task.service.implementation;

import htec.task.model.User;
import htec.task.repository.UserRepository;
import htec.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}

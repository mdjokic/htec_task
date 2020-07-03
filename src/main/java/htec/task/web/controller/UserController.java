package htec.task.web.controller;

import htec.task.exception.UsernameAlreadyExistsException;
import htec.task.mapper.user.UserDTOMapper;
import htec.task.model.User;
import htec.task.service.UserService;
import htec.task.web.dto.user.UserPostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDTOMapper userMapper;

    @RequestMapping(method = RequestMethod.POST, consumes="application/json")
    public ResponseEntity registerAUser(@Valid @RequestBody UserPostDTO newUser){
        if (usernameAlreadyExists(newUser.getUsername())){
            throw new UsernameAlreadyExistsException();
        }
        User user = userMapper.toEntity(newUser);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private Boolean usernameAlreadyExists(String username){
        User user = userService.findByUsername(username);
        return user != null;
    }


}

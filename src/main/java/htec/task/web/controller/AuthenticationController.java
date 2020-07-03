package htec.task.web.controller;


import htec.task.model.User;
import htec.task.security.TokenUtils;
import htec.task.service.UserService;
import htec.task.web.dto.auth.AuthPostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    TokenUtils tokenUtils;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> authentication(@Valid @RequestBody AuthPostDTO loginInfo){
        try {
            String token = tryToAuthenticate(loginInfo);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Login failed; Invalid username or password", HttpStatus.BAD_REQUEST);
        }
    }

    private String tryToAuthenticate(AuthPostDTO loginInfo) {
        User user = userService.findByUsername(loginInfo.getUsername());
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                user.getUsername(), loginInfo.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);
        UserDetails details = userDetailsService.loadUserByUsername(user.getUsername());
        return tokenUtils.generateToken(details, user.getRole() );
    }


}

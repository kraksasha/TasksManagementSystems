package com.example.tasksmanagementsystems.Controller;

import com.example.tasksmanagementsystems.DTO.JwtRequest;
import com.example.tasksmanagementsystems.DTO.JwtResponse;
import com.example.tasksmanagementsystems.Entity.User;
import com.example.tasksmanagementsystems.Service.UserService;
import com.example.tasksmanagementsystems.Utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private UserService userService;
    private JwtTokenUtils jwtTokenUtils;
    private PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, JwtTokenUtils jwtTokenUtils, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtTokenUtils = jwtTokenUtils;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(value = "/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest jwtRequest){
        UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getEmail());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping(value = "/addNewUser")
    public ResponseEntity<?> registrationNewUser(@RequestBody User user){
        if (userService.uniqueOrNotEmail(user)){
            String passwordEncode = passwordEncoder.encode(user.getPassword());
            user.setPassword(passwordEncode);
            userService.regNewUser(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Пользователь с таким email существует",HttpStatus.BAD_REQUEST);
    }
}

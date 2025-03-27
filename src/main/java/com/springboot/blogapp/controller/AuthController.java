package com.springboot.blogapp.controller;

import com.springboot.blogapp.payload.JWTAuthResponse;
import com.springboot.blogapp.payload.LoginDto;
import com.springboot.blogapp.payload.RegisterDto;
import com.springboot.blogapp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    // Build Login REST API
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto){
        String username = loginDto.getUsername();
        String token = authService.login(loginDto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        jwtAuthResponse.setUsername(username);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    // Build Register REST API
    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity register(@RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
        return new ResponseEntity(HttpStatus.OK);
    }

}

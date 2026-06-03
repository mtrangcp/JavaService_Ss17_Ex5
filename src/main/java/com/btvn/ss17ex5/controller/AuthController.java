package com.btvn.ss17ex5.controller;

import com.btvn.ss17ex5.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public String mockLogin() {
        UserDetails mockUser = new User(
                "trang_ha",
                "password",
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_PLAYER"))
        );

        String token = tokenProvider.generateToken(mockUser);

        return token;
    }
}
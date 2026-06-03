package com.btvn.ss17ex5.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("trang_ha".equals(username)) {
            return new User(
                    "trang_ha",
                    "{noop}123456",
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_PLAYER"))
            );
        }

        if ("admin_chu".equals(username)) {
            return new User(
                    "admin_chu",
                    "{noop}123456",
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))
            );
        }

        throw new UsernameNotFoundException("Không tìm thấy người dùng: " + username);
    }
}
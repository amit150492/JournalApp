package com.adhrit.journalApp.service;

import com.adhrit.journalApp.Repository.UserRepository;
import com.adhrit.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            List<String> roles = user.getRoles();
            if (roles == null || roles.isEmpty()) {
                roles = Collections.singletonList("USER");
            }
            UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles(roles.toArray(new String[0]))
                    .build();
            return userDetails;
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}

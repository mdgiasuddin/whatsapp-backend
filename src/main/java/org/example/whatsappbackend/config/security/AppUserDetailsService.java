package org.example.whatsappbackend.config.security;

import lombok.RequiredArgsConstructor;
import org.example.whatsappbackend.exception.ApplicationException;
import org.example.whatsappbackend.model.entity.User;
import org.example.whatsappbackend.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new ApplicationException(
                        "USER_NOT_FOUND", String.format("No user found by username: %s", username)
                ));

        return new org.springframework.security.core.userdetails.User(
                username, user.getPassword(), new ArrayList<>()
        );
    }
}

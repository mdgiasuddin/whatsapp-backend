package org.example.whatsappbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.whatsappbackend.config.security.JwtService;
import org.example.whatsappbackend.exception.ApplicationException;
import org.example.whatsappbackend.model.dto.request.SignInRequest;
import org.example.whatsappbackend.model.dto.request.UserCreateRequest;
import org.example.whatsappbackend.model.dto.response.AuthResponse;
import org.example.whatsappbackend.model.entity.User;
import org.example.whatsappbackend.repository.UserRepository;
import org.example.whatsappbackend.service.AuthenticationService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    @Override
    public AuthResponse registerNewUser(UserCreateRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new ApplicationException("USER_ALREADY_EXISTS", String.format("User already exists by email: %s", request.email()));
        }

        User user = new User();
        user.setFullName(request.fullName());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));

        userRepository.save(user);

        String jwt = jwtService.generateAccessToken(request.email());
        return new AuthResponse(jwt, true);
    }

    @Override
    public AuthResponse signIn(SignInRequest request) {
        User user = userRepository.findByEmail(request.email())
                .filter(u -> passwordEncoder.matches(request.password(), u.getPassword()))
                .orElseThrow(() -> new ApplicationException("INVALID_CREDENTIAL", "Invalid username or password"));

        String jwt = jwtService.generateAccessToken(user.getEmail());
        return new AuthResponse(jwt, true);
    }
}

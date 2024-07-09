package org.example.whatsappbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.whatsappbackend.model.dto.request.SignInRequest;
import org.example.whatsappbackend.model.dto.request.UserCreateRequest;
import org.example.whatsappbackend.model.dto.response.AuthResponse;
import org.example.whatsappbackend.service.AuthenticationService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public AuthResponse registerNewUser(@RequestBody @Validated UserCreateRequest request) {
        return authenticationService.registerNewUser(request);
    }

    @PostMapping("/signIn")
    public AuthResponse signIn(@RequestBody @Validated SignInRequest request) {
        return authenticationService.signIn(request);
    }
}

package org.example.whatsappbackend.service;

import org.example.whatsappbackend.model.dto.request.LoginRequest;
import org.example.whatsappbackend.model.dto.request.UserCreateRequest;
import org.example.whatsappbackend.model.dto.response.AuthResponse;

public interface AuthenticationService {
    AuthResponse registerNewUser(UserCreateRequest request);

    AuthResponse signIn(LoginRequest request);
}

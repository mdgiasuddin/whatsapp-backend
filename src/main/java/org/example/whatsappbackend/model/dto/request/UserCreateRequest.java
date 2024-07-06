package org.example.whatsappbackend.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserCreateRequest(
        @NotBlank
        String fullName,
        @NotBlank
        @Email
        String email,
        String profilePicture,
        @NotBlank
        String password
) {
}

package org.example.whatsappbackend.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserUpdateRequest(
        @NotBlank
        String fullName,
        @NotBlank
        String profilePicture
) {
}

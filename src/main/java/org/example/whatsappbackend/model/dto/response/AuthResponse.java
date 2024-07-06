package org.example.whatsappbackend.model.dto.response;

public record AuthResponse(
        String jwt,
        boolean authenticated
) {
}

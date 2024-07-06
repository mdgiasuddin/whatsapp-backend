package org.example.whatsappbackend.model.dto.response;

public record ApiResponse(
        String message,
        boolean status
) {
}

package org.example.whatsappbackend.exception;

import java.time.LocalDateTime;

public record ExceptionResponse(
        String code,
        String message,
        LocalDateTime timestamp
) {
}

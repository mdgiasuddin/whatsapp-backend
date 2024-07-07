package org.example.whatsappbackend.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SendMessageRequest(
        @NotNull
        Integer chatId,
        @NotBlank
        String content
) {
}

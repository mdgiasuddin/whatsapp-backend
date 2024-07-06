package org.example.whatsappbackend.model.dto.request;

import jakarta.validation.constraints.NotNull;

public record GroupUserAddRequest(
        @NotNull
        Integer chatId,
        @NotNull
        Integer userId
) {
}

package org.example.whatsappbackend.model.dto.request;

import jakarta.validation.constraints.NotNull;

public record SingleChatCreateRequest(
        @NotNull
        Integer userId
) {
}

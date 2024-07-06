package org.example.whatsappbackend.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record GroupChatCreateRequest(
        @NotNull
        List<Integer> userIds,
        @NotBlank
        String chatName,
        String chatImage
) {
}

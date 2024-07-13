package org.example.whatsappbackend.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record GroupChatCreateRequest(
        @NotEmpty
        List<Integer> userIds,
        @NotBlank
        String chatName,
        String chatImage
) {
}

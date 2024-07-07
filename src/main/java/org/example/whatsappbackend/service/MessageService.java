package org.example.whatsappbackend.service;

import org.example.whatsappbackend.model.dto.request.SendMessageRequest;
import org.example.whatsappbackend.model.dto.response.ApiResponse;
import org.example.whatsappbackend.model.dto.response.MessageResponse;

import java.util.List;

public interface MessageService {
    MessageResponse sendMessage(SendMessageRequest request);

    List<MessageResponse> getChatMessages(Integer chatId);

    MessageResponse findByMessageId(Integer messageId);

    ApiResponse deleteMessage(Integer messageId);
}

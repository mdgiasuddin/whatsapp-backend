package org.example.whatsappbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.whatsappbackend.exception.ApplicationException;
import org.example.whatsappbackend.model.dto.request.SendMessageRequest;
import org.example.whatsappbackend.model.dto.response.ApiResponse;
import org.example.whatsappbackend.model.dto.response.MessageResponse;
import org.example.whatsappbackend.model.entity.Chat;
import org.example.whatsappbackend.model.entity.Message;
import org.example.whatsappbackend.model.entity.User;
import org.example.whatsappbackend.repository.MessageRepository;
import org.example.whatsappbackend.service.ChatService;
import org.example.whatsappbackend.service.MessageService;
import org.example.whatsappbackend.util.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final ChatService chatService;
    private final SecurityUtil securityUtil;

    @Override
    public MessageResponse sendMessage(SendMessageRequest request) {
        User user = securityUtil.getCurrentLoggedInUser();
        Chat chat = chatService.getChatById(request.chatId());

        Message message = new Message();
        message.setChat(chat);
        message.setUser(user);
        message.setContent(request.content());

        return new MessageResponse(messageRepository.save(message));
    }

    @Override
    public List<MessageResponse> getChatMessages(Integer chatId) {
        User user = securityUtil.getCurrentLoggedInUser();
        Chat chat = chatService.getChatById(chatId);
        if (!chat.getParticipants().contains(user)) {
            throw new ApplicationException("PERMISSION_DENIED", "You are not permitted to view the message");
        }

        List<Message> messages = messageRepository.findByChatId(chatId);

        List<MessageResponse> responses = new ArrayList<>();
        for (Message message : messages) {
            responses.add(new MessageResponse(message));
        }
        return responses;
    }

    @Override
    public MessageResponse findByMessageId(Integer messageId) {
        Message message = getMessageById(messageId);

        return new MessageResponse(message);
    }

    private Message getMessageById(Integer messageId) {
        return messageRepository.findById(messageId)
                .orElseThrow(() -> new ApplicationException(
                        "MESSAGE_NOT_FOUND", String.format("No message found by id: %d", messageId))
                );
    }

    @Override
    public ApiResponse deleteMessage(Integer messageId) {
        User user = securityUtil.getCurrentLoggedInUser();
        Message message = getMessageById(messageId);
        if (message.getUser().equals(user)) {
            messageRepository.delete(message);
            return new ApiResponse("Message deleted successfully", true);
        }

        throw new ApplicationException("PERMISSION_DENIED", "You are not permitted to delete the message");
    }
}

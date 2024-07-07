package org.example.whatsappbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.whatsappbackend.model.dto.request.SendMessageRequest;
import org.example.whatsappbackend.model.dto.response.ApiResponse;
import org.example.whatsappbackend.model.dto.response.MessageResponse;
import org.example.whatsappbackend.service.MessageService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/send")
    public MessageResponse sendMessage(@RequestBody @Validated SendMessageRequest request) {
        return messageService.sendMessage(request);
    }

    @GetMapping("/chat/{chatId}")
    public List<MessageResponse> getChatMessages(@PathVariable int chatId) {
        return messageService.getChatMessages(chatId);
    }

    @DeleteMapping("/{messageId}")
    public ApiResponse deleteMessage(@PathVariable int messageId) {
        return messageService.deleteMessage(messageId);
    }
}

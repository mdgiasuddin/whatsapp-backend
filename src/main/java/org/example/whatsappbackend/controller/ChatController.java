package org.example.whatsappbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.whatsappbackend.model.dto.request.GroupChatCreateRequest;
import org.example.whatsappbackend.model.dto.request.GroupUserAddRequest;
import org.example.whatsappbackend.model.dto.request.SingleChatCreateRequest;
import org.example.whatsappbackend.model.dto.response.ChatResponse;
import org.example.whatsappbackend.service.ChatService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chats")
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/single")
    public ChatResponse createOneToOneChat(@RequestBody @Validated SingleChatCreateRequest request) {
        return chatService.createOneToOneChat(request);
    }

    @PostMapping("/group")
    public ChatResponse createGroupChat(@RequestBody @Validated GroupChatCreateRequest request) {
        return chatService.createGroupChat(request);
    }

    @GetMapping("/details/{id}")
    public ChatResponse getChatById(@PathVariable int id) {
        return chatService.findByChatId(id);
    }

    @GetMapping("/users")
    public List<ChatResponse> getChatsByUserId() {
        return chatService.findChatByUser();
    }

    @PutMapping("/groups/add/users")
    public ChatResponse addUserToGroup(@RequestBody @Validated GroupUserAddRequest request) {
        return chatService.addUserToGroup(request);
    }
}

package org.example.whatsappbackend.service;

import org.example.whatsappbackend.model.dto.request.GroupChatCreateRequest;
import org.example.whatsappbackend.model.dto.request.GroupUserAddRequest;
import org.example.whatsappbackend.model.dto.request.SingleChatCreateRequest;
import org.example.whatsappbackend.model.dto.response.ChatResponse;

import java.util.List;

public interface ChatService {
    ChatResponse createOneToOneChat(SingleChatCreateRequest request);

    ChatResponse findByChatId(Integer chatId);

    List<ChatResponse> findChatByUser();

    ChatResponse createGroupChat(GroupChatCreateRequest request);

    ChatResponse addUserToGroup(GroupUserAddRequest request);

    ChatResponse renameGroup(Integer chatId, String groupName);

    ChatResponse removeUserFromGroup(Integer chatId, Integer userId, Integer reqUserId);

    void deleteChat(Integer chatId);
}

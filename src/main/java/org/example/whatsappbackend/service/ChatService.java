package org.example.whatsappbackend.service;

import org.example.whatsappbackend.model.dto.request.GroupChatCreateRequest;
import org.example.whatsappbackend.model.dto.request.GroupUserAddRequest;
import org.example.whatsappbackend.model.dto.request.GroupUserRemoveRequest;
import org.example.whatsappbackend.model.dto.request.SingleChatCreateRequest;
import org.example.whatsappbackend.model.dto.response.ApiResponse;
import org.example.whatsappbackend.model.dto.response.ChatResponse;
import org.example.whatsappbackend.model.entity.Chat;

import java.util.List;

public interface ChatService {
    ChatResponse createOneToOneChat(SingleChatCreateRequest request);

    ChatResponse findByChatId(Integer chatId);

    Chat getChatById(Integer chatId);

    List<ChatResponse> findChatByUser();

    ChatResponse createGroupChat(GroupChatCreateRequest request);

    ChatResponse addUserToGroup(GroupUserAddRequest request);

    ChatResponse renameGroup(Integer chatId, String groupName);

    ChatResponse removeUserFromGroup(GroupUserRemoveRequest request);

    ApiResponse deleteChat(Integer chatId);
}

package org.example.whatsappbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.whatsappbackend.exception.ApplicationException;
import org.example.whatsappbackend.model.dto.request.GroupChatCreateRequest;
import org.example.whatsappbackend.model.dto.request.GroupUserAddRequest;
import org.example.whatsappbackend.model.dto.request.GroupUserRemoveRequest;
import org.example.whatsappbackend.model.dto.request.SingleChatCreateRequest;
import org.example.whatsappbackend.model.dto.response.ApiResponse;
import org.example.whatsappbackend.model.dto.response.ChatResponse;
import org.example.whatsappbackend.model.entity.Chat;
import org.example.whatsappbackend.model.entity.User;
import org.example.whatsappbackend.repository.ChatRepository;
import org.example.whatsappbackend.repository.UserRepository;
import org.example.whatsappbackend.service.ChatService;
import org.example.whatsappbackend.service.UserService;
import org.example.whatsappbackend.util.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final SecurityUtil securityUtil;

    @Override
    public ChatResponse createOneToOneChat(SingleChatCreateRequest request) {
        User reqUser = securityUtil.getCurrentLoggedInUser();
        if (reqUser.getId() == request.userId()) {
            throw new ApplicationException("SAME_USER", "Requested user and current user are same");
        }

        User receiverUser = userService.findUserById(request.userId());
        Chat chat = chatRepository.findSingleChatByUsers(reqUser, receiverUser);
        if (chat != null) {
            return new ChatResponse(chat);
        }

        chat = new Chat();
        chat.setCreatedBy(reqUser);
        chat.getParticipants().add(reqUser);
        chat.getParticipants().add(receiverUser);
        chat.setGroupChat(false);

        return new ChatResponse(chatRepository.save(chat));
    }

    @Override
    public ChatResponse findByChatId(Integer chatId) {
        Chat chat = getChatById(chatId);

        return new ChatResponse(chat);
    }

    @Override
    public Chat getChatById(Integer chatId) {
        return chatRepository.findById(chatId)
                .orElseThrow(
                        () -> new ApplicationException("CHAT_NOT_FOUND", String.format("No chat found by id %d", chatId))
                );
    }

    @Override
    public List<ChatResponse> findChatByUser() {
        User reqUser = securityUtil.getCurrentLoggedInUser();
        List<Chat> chats = chatRepository.findChatByUser(reqUser);
        List<ChatResponse> chatResponses = new ArrayList<>();

        for (Chat chat : chats) {
            chatResponses.add(new ChatResponse(chat, chat.getParticipants()));
        }
        return chatResponses;
    }

    @Override
    public ChatResponse createGroupChat(GroupChatCreateRequest request) {
        User reqUser = securityUtil.getCurrentLoggedInUser();
        List<User> users = userRepository.findByIdIn(request.userIds());

        Chat groupChat = new Chat();
        groupChat.setGroupChat(true);
        groupChat.setImage(request.chatImage());
        groupChat.setName(request.chatName());
        groupChat.setCreatedBy(reqUser);
        groupChat.getParticipants().add(reqUser);
        groupChat.getParticipants().addAll(users);

        return new ChatResponse(chatRepository.save(groupChat));
    }

    @Override
    public ChatResponse addUserToGroup(GroupUserAddRequest request) {
        User reqUser = securityUtil.getCurrentLoggedInUser();
        Chat groupChat = getChatById(request.chatId());
        User user = userService.findUserById(request.userId());

        if (groupChat.getAdmins().contains(reqUser)) {
            groupChat.getParticipants().add(user);
            return new ChatResponse(chatRepository.save(groupChat));
        }

        throw new ApplicationException("PERMISSION_DENIED", "Only admin can add new user");
    }

    @Override
    public ChatResponse renameGroup(Integer chatId, String groupName) {
        User reqUser = securityUtil.getCurrentLoggedInUser();
        Chat groupChat = getChatById(chatId);

        if (groupChat.getParticipants().contains(reqUser)) {
            groupChat.setName(groupName);
            return new ChatResponse(chatRepository.save(groupChat));
        }

        throw new ApplicationException("PERMISSION_DENIED", "Only group members can update group name");
    }

    @Override
    public ChatResponse removeUserFromGroup(GroupUserRemoveRequest request) {
        User reqUser = securityUtil.getCurrentLoggedInUser();
        Chat groupChat = getChatById(request.chatId());
        User user = userService.findUserById(request.userId());

        if ((groupChat.getParticipants().contains(reqUser) && reqUser.getId() == user.getId()) ||
                groupChat.getAdmins().contains(reqUser)) {
            groupChat.getParticipants().remove(user);
            return new ChatResponse(chatRepository.save(groupChat));
        }

        throw new ApplicationException("PERMISSION_DENIED", "Only admin/self can remove a user");
    }

    @Override
    public ApiResponse deleteChat(Integer chatId) {
        User reqUser = securityUtil.getCurrentLoggedInUser();
        Chat chat = getChatById(chatId);
        if (chat.getAdmins().contains(reqUser)) {
            chatRepository.delete(chat);
            return new ApiResponse("Chat deleted successfully", true);
        }

        throw new ApplicationException("PERMISSION_DENIED", "Only admin can delete a chat");
    }
}

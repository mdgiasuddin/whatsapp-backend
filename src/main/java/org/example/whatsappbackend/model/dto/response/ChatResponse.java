package org.example.whatsappbackend.model.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.example.whatsappbackend.model.entity.Chat;
import org.example.whatsappbackend.model.entity.Message;
import org.example.whatsappbackend.model.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ChatResponse {
    private Integer id;
    private String name;
    private String image;
    private boolean groupChat;
    private UserResponse createdBy;
    private List<UserResponse> participants;
    private List<UserResponse> admins;
    private List<MessageResponse> messages;

    public ChatResponse(Chat chat) {
        this.id = chat.getId();
        this.name = chat.getName();
        this.image = chat.getImage();
        this.groupChat = chat.isGroupChat();
        this.participants = new ArrayList<>();
        this.admins = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    public ChatResponse(Chat chat, Set<User> participants) {
        this(chat);
        for (User user : participants) {
            this.participants.add(new UserResponse(user));
        }

    }

    public ChatResponse(Chat chat, User createdBy, Set<User> participants, Set<User> admins, List<Message> messages) {
        this(chat);
        this.createdBy = new UserResponse(createdBy);
        for (User user : participants) {
            this.participants.add(new UserResponse(user));
        }
        for (User user : admins) {
            this.admins.add(new UserResponse(user));
        }
        for (Message message : messages) {
            this.messages.add(new MessageResponse(message));
        }

    }
}

package org.example.whatsappbackend.model.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.example.whatsappbackend.model.entity.Message;

import java.time.LocalDateTime;

@Getter
@Setter
public class MessageResponse {
    private Integer id;
    private String content;
    private LocalDateTime timestamp;
    private UserResponse user;

    public MessageResponse(Message message) {
        this.id = message.getId();
        this.content = message.getContent();
        this.timestamp = message.getTimestamp();
        this.user = new UserResponse(message.getUser());
    }
}

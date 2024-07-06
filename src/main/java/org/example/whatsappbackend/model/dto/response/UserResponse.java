package org.example.whatsappbackend.model.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.example.whatsappbackend.model.entity.User;

@Getter
@Setter
public class UserResponse {
    private int id;
    private String fullName;
    private String email;
    private String profilePicture;

    public UserResponse(User user) {
        this.id = user.getId();
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.profilePicture = user.getProfilePicture();
    }
}

package org.example.whatsappbackend.service;

import org.example.whatsappbackend.model.dto.request.UserUpdateRequest;
import org.example.whatsappbackend.model.dto.response.UserResponse;
import org.example.whatsappbackend.model.entity.User;

import java.util.List;

public interface UserService {

    User findUserById(Integer id);

    UserResponse getUserProfile();

    void updateUser(UserUpdateRequest request);

    List<UserResponse> searchUser(String query);
}

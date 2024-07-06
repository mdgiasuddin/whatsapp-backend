package org.example.whatsappbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.whatsappbackend.config.security.JwtService;
import org.example.whatsappbackend.exception.ApplicationException;
import org.example.whatsappbackend.model.dto.request.UserUpdateRequest;
import org.example.whatsappbackend.model.dto.response.UserResponse;
import org.example.whatsappbackend.model.entity.User;
import org.example.whatsappbackend.repository.UserRepository;
import org.example.whatsappbackend.service.UserService;
import org.example.whatsappbackend.util.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final SecurityUtil securityUtil;

    @Override
    public User findUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(
                        "USER_NOT_FOUND", String.format("No user found by id: %d", id)
                ));
    }

    @Override
    public UserResponse getUserProfile() {
        return new UserResponse(securityUtil.getCurrentLoggedInUser());
    }

    @Override
    public void updateUser(UserUpdateRequest request) {
        User user = securityUtil.getCurrentLoggedInUser();
        user.setFullName(request.fullName());
        user.setProfilePicture(request.profilePicture());
        userRepository.save(user);
    }

    @Override
    public List<UserResponse> searchUser(String query) {
        List<User> users = userRepository.searchUser("%" + query + "%");

        List<UserResponse> userResponses = new ArrayList<>(users.size());
        for (User user : users) {
            userResponses.add(new UserResponse(user));
        }

        return userResponses;
    }
}

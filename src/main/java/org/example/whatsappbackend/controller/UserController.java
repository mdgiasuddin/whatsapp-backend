package org.example.whatsappbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.whatsappbackend.model.dto.request.UserUpdateRequest;
import org.example.whatsappbackend.model.dto.response.ApiResponse;
import org.example.whatsappbackend.model.dto.response.UserResponse;
import org.example.whatsappbackend.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public UserResponse getUserProfile() {
        return userService.getUserProfile();
    }

    @GetMapping("/search/{query}")
    public List<UserResponse> searchUser(@PathVariable String query) {
        return userService.searchUser(query);
    }

    @PutMapping
    public ApiResponse updateUser(@RequestBody @Validated UserUpdateRequest request) {
        userService.updateUser(request);
        return new ApiResponse("User update successfully", true);
    }
}

package com.culturestamp.back.auth.api.controller;


import com.culturestamp.back.auth.api.service.UserOAuthService;
import com.culturestamp.back.dto.UserResponse;
import com.culturestamp.back.dto.UserServiceResponse;
import com.culturestamp.back.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    final private UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl, UserOAuthService userOAuthService) {
        this.userServiceImpl = userServiceImpl;
    }


    // TODO: final 문의
    @GetMapping("/user/{userId}")
    public UserResponse getUserById(@PathVariable String userId) {
        final UserServiceResponse userServiceResponse = userServiceImpl.getUserById(Long.valueOf(userId));
        return new UserResponse(userServiceResponse);
    }


}

package com.culturestamp.back.controller;


import com.culturestamp.back.auth.dto.SessionUser;
import com.culturestamp.back.dto.UserResponse;
import com.culturestamp.back.dto.UserServiceResponse;
import com.culturestamp.back.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userServiceImpl;
    private final HttpSession httpSession;

    // TODO: final 문의
    @GetMapping("/{userId}")
    public UserResponse getUserById(@PathVariable String userId) {
        final UserServiceResponse userServiceResponse = userServiceImpl.getUserById(Long.valueOf(userId));
        return new UserResponse(userServiceResponse);
    }

}

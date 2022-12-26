package com.culturestamp.back.service;


import com.culturestamp.back.dto.UserResponse;
import com.culturestamp.back.dto.UserServiceResponse;

//
public interface UserService {
    public UserServiceResponse getUserById(Long userId);
}

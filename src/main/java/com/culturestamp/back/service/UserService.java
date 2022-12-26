package com.culturestamp.back.service;


import com.culturestamp.back.dto.UserServiceResponse;

public interface UserService {
    public UserServiceResponse getUserById(Long userId);
    public UserServiceResponse getUserByEmail(String email);
    public UserServiceResponse getUser(String userId);
}
package com.culturestamp.back.service.impl;

import com.culturestamp.back.dto.UserServiceResponse;
import com.culturestamp.back.entity.User;
import com.culturestamp.back.repository.UserRepository;
import com.culturestamp.back.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserServiceResponse getUserById(Long userId) {
        User user = userRepository.findById(userId)
                                    .orElseThrow(() -> new IllegalStateException("User ID에 해당하는 사용자가 없습니다."));
        return new UserServiceResponse(user);
    }

    @Override
    public UserServiceResponse getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserServiceResponse getUser(String loginId) {
        return userRepository.findByLoginId(loginId);
    }
}

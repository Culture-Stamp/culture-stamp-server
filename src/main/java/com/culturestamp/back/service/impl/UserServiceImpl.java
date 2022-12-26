package com.culturestamp.back.service.impl;

import com.culturestamp.back.dto.UserResponse;
import com.culturestamp.back.dto.UserServiceResponse;
import com.culturestamp.back.entity.User;
import com.culturestamp.back.repository.UserRepository;
import com.culturestamp.back.service.UserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserServiceResponse getUserById(Long userId) {
        User user = userRepository.findById(userId)
                                    .orElseThrow(() -> new IllegalStateException("User ID에 해당하는 사용자가 없습니다."));
        return new UserServiceResponse(user);
    }
}

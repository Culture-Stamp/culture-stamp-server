package com.culturestamp.back.repository;

import com.culturestamp.back.dto.UserServiceResponse;
import com.culturestamp.back.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {
    UserServiceResponse findByEmail(String email);
    UserServiceResponse findByUserId(String userId);
    UserServiceResponse findByLoginId(String loginId);
}
package com.culturestamp.back.auth.dto;

import com.culturestamp.back.entity.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String nickname;
    private String email;

    public SessionUser(User user) {
        this.nickname = user.getNickname();
        this.email = user.getEmail();
    }
}

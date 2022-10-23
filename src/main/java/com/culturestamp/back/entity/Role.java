package com.culturestamp.back.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;


@Getter
@RequiredArgsConstructor
public enum Role {

    ADMIN("ROLE_ADMIN","관리자"),
    USER("ROLE_USER", "일반 사용자"),
    GUEST("ROLE_GUEST", "손님");

    private final String key;
    private final String title;
}

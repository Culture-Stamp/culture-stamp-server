package com.culturestamp.back.dto;

import java.util.Date;

import com.culturestamp.back.entity.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserServiceResponse {

	private Long userId;
	private String nickname;
	private String loginId;
	private String email;
	private String password;
	private Date lastLoginAt;
	private int failCount;

	@Builder
	public UserServiceResponse(User user) {
		this.userId = user.getUserId();
		this.nickname = user.getNickname();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.lastLoginAt = user.getLastLoginAt();
	}
}


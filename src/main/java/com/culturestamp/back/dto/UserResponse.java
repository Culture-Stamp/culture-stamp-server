package com.culturestamp.back.dto;

import com.culturestamp.back.controller.request.ReviewRequest;
import com.culturestamp.back.entity.Review;
import com.culturestamp.back.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {

	private String nickname;
	private String loginId;
	private String email;
	private String password;
	private Date lastLoginAt;
	private int failCount;

	@Builder
	public UserResponse(UserServiceResponse user) {
		this.nickname = user.getNickname();
		this.loginId = user.getLoginId();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.lastLoginAt = user.getLastLoginAt();
		this.failCount = user.getFailCount();
	}


}

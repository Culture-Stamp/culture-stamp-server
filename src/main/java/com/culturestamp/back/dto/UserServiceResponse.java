package com.culturestamp.back.dto;

import com.culturestamp.back.entity.User;
import lombok.*;

import java.util.Date;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserServiceResponse {

	private Long userId;
	private String nickname;
	private String loginId;
	private String providerType;
//	private String providerId;
	private String email;
	private String password;
	private String role;
	private Date lastLoginAt;
	private int failCount;

	public UserServiceResponse(User user) {
		this.userId = user.getUserId();
		this.nickname = user.getNickname();
		this.providerType = user.getProviderType();
//		this.providerId = user.getProviderId();
		this.loginId = user.getLoginId();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.lastLoginAt = user.getLastLoginAt();
	}

	public UserServiceResponse(String email, String nickname) {
		this.email = email;
		this.nickname = nickname;
	}

	public UserServiceResponse update(String name) {
		this.nickname = name;
		return this;
	}

}


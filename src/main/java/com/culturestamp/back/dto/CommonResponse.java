package com.culturestamp.back.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;

@Getter
@Setter
public class CommonResponse<T> {
	//private ResponseType type;
	private String message;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	T result;

	public CommonResponse(String message, @Nullable T result) {
		this.message = message;
		this.result = result;
	}
}
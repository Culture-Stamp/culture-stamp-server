package com.culturestamp.back.controller.request;

import java.time.LocalDateTime;
import java.util.Date;

import com.culturestamp.back.entity.Todo;
import com.culturestamp.back.entity.User;
import lombok.*;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class TodoRequest {
	private User user;
	private String content;
	private LocalDateTime date;
	private int doneFlag;


	public Todo toEntity(TodoRequest todoRequest) {
		return Todo.builder()
			.user(todoRequest.user)
			.content(todoRequest.content)
			.date(todoRequest.date)
			.doneFlag(todoRequest.doneFlag)
			.build();
	}
}

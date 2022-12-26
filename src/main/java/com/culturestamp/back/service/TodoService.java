package com.culturestamp.back.service;

import java.util.List;

import com.culturestamp.back.controller.request.TodoRequest;
import com.culturestamp.back.dto.TodoResponse;
import com.culturestamp.back.entity.Todo;

public interface TodoService {
	//
	TodoResponse addTodo(TodoRequest todoRequest);
	List<Todo> findAllTodo();
	TodoResponse findTodo(Long todoId);
	void removeTodo(Long todoId);
	TodoResponse modifyTodo(Long todoId, TodoRequest todoRequest) throws Exception;
}

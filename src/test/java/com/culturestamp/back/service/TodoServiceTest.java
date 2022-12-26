package com.culturestamp.back.service;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import com.culturestamp.back.controller.request.ReviewEditorRequest;
import com.culturestamp.back.controller.request.TodoRequest;
import com.culturestamp.back.dto.ReviewResponse;
import com.culturestamp.back.dto.TodoResponse;
import com.culturestamp.back.entity.Category;
import com.culturestamp.back.entity.Review;
import com.culturestamp.back.entity.Role;
import com.culturestamp.back.entity.Todo;
import com.culturestamp.back.entity.User;
import com.culturestamp.back.repository.ReviewRepository;
import com.culturestamp.back.repository.TodoRepository;
import com.culturestamp.back.repository.UserRepository;
import com.culturestamp.back.service.impl.ReviewServiceImpl;
import com.culturestamp.back.service.impl.TodoServiceImpl;

@SpringBootTest
public class TodoServiceTest {
	@Autowired
	private TodoRepository repository;

	@Autowired
	private TodoServiceImpl service;

	@Autowired
	private UserRepository userRepository;

	private User user;
	private Todo todo;

	@BeforeEach
	@DisplayName("user, todo 데이터 설정")
	void setup() throws ParseException {
		user = User.builder()
			.nickname("별명")
			.loginId("testID")
			.email("이메일@naver.com")
			.password("wtefsfd")
			.role(Role.USER)
			.lastLoginAt(new SimpleDateFormat("yyyyMMdd").parse("20221028"))
			.failCount(0)
			.build();
		userRepository.save(user);

		todo = Todo.builder()
			.content("영화 보기")
			.date(LocalDateTime.now())
			.doneFlag(1)
			.user(user)
			.build();

	}
	@Test
	void testMock객체생성(){
		assertNotNull(service);
	}

	@Test
	void test_todo_등록_서비스() throws Exception{
		//given
		TodoRequest request = TodoRequest.builder()
			.content(todo.getContent())
			.date(todo.getDate())
			.doneFlag(todo.getDoneFlag())
			.user(todo.getUser())
			.build();

		// when
		service.addTodo(request);

		// then
		Todo actual = repository.findAll().get(0);
		System.out.println(actual.getId());
	}
	@Test
	@DisplayName("Todo 전체 조회")
	void todo_전체_조회(){
		// given
		List<Todo> requestTodo = (List<Todo>)IntStream.range(1, 10)
			.mapToObj(todo -> Todo.builder()
				.content("영화 보기2")
				.date(LocalDateTime.now())
				.doneFlag(0)
				.user(user)
				.build()
			).toList();

		// when
		List<Todo> todo = service.findAllTodo();

		// then
		System.out.println(todo.size() );
	}

	@Test
	void test_todo_단건_조회() {
		// given
		repository.save(todo);

		// when
		TodoResponse response = service.findTodo(todo.getId());

		// then
		assertNotNull(response);
		assertEquals( "영화 보기", response.getContent());
		assertEquals( 1, response.getDoneFlag());
	}

	@Test
	void test_todo_삭제(){
		// given
		repository.save(todo);

		// when
		service.removeTodo( todo.getId() );

		// then
		assertEquals( true, repository.findById(todo.getId()).isEmpty() );
	}


	@Test
	void test_todo_수정() throws Exception {
		// given
		repository.save(todo);

		TodoRequest todoRequest = TodoRequest.builder()
			.content("todo 테스트 내용 수정")
			.doneFlag(0)
			.build();

		// when
		service.modifyTodo( todo.getId(), todoRequest );

		// then
		Todo changeTodo = repository.findById(todo.getId()).orElseThrow( () -> new RuntimeException("존재 X todo ID = "+todo.getId()) );


		assertEquals( "todo 테스트 내용 수정", changeTodo.getContent() );
		assertEquals( 0, changeTodo.getDoneFlag() );
	}

}

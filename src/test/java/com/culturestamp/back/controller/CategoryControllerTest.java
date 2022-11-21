package com.culturestamp.back.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.culturestamp.back.controller.request.CategoryRequest;
import com.culturestamp.back.entity.Category;
import com.culturestamp.back.entity.Review;
import com.culturestamp.back.entity.Role;
import com.culturestamp.back.entity.User;
import com.culturestamp.back.repository.CategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CategoryControllerTest {
	@Autowired
	private CategoryRepository repository;

	@Autowired
	// 설정 serialize
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;

	private User user;
	private Category category;

	// 테스트 메서드 실행 이전에 수행된다.
	@BeforeEach
	@DisplayName("user, category 데이터 설정")
	void setup() throws ParseException {
		user = User.builder()
			.userId(1L)
			.nickname("가가")
			.loginId("testID")
			.email("이메일@naver.com")
			.password("passwww")
			.role(Role.USER)
			.lastLoginAt(new SimpleDateFormat("yyyyMMdd").parse("20221028"))
			.failCount(0)
			.build();

		category = new Category("Movie",3L,user);

	}


	@Test
	public void test카테고리_등록_컨트롤러() throws Exception {
		//given
		CategoryRequest request = CategoryRequest.builder()
			.categoryName(category.getCategoryName())
			.reviewCount(category.getReviewCount())
			.user(category.getUser())
			.build();
		//
		//when
		mockMvc.perform(MockMvcRequestBuilders
				.post("/category")
				.content(objectMapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andDo(print());

		//then
		Category actual = repository.findAll().get(0);
		assertEquals(1L, actual.getCategoryId());
	}
	}

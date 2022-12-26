package com.culturestamp.back.service;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;

import com.culturestamp.back.controller.request.CategoryRequest;
import com.culturestamp.back.controller.request.TodoRequest;
import com.culturestamp.back.dto.CategoryResponse;
import com.culturestamp.back.dto.ReviewResponse;
import com.culturestamp.back.entity.Category;
import com.culturestamp.back.entity.Review;
import com.culturestamp.back.entity.Role;
import com.culturestamp.back.entity.Todo;
import com.culturestamp.back.entity.User;
import com.culturestamp.back.repository.CategoryRepository;
import com.culturestamp.back.repository.ReviewRepository;
import com.culturestamp.back.repository.UserRepository;
import com.culturestamp.back.service.impl.CategoryServiceImpl;

@ActiveProfiles("test")
@SpringBootTest
public class CategoryServiceTest {
	@Autowired
	private CategoryRepository repository;

	@Autowired
	private CategoryServiceImpl service;

	@Autowired
	private UserRepository userRepository;

	private User user;
	private Category category;

	@BeforeEach
	@DisplayName("user,category 데이터 설정")
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

		category = Category.builder()
				.categoryName("Movie")
				.reviewCount(5L)
				.build();

	}
	@Test
	void testMock객체생성(){
		assertNotNull(service);
	}
	// 어떤 상태에서 출발 (given) 하여 어떤 상태의 변화를 가했을 때 (when) 기대하는 어떠한 상태가 되어야 한다.(then)
	@Test
	void testcategory_등록_서비스() throws Exception {
		//given
		userRepository.save(user);
		CategoryRequest request = CategoryRequest.builder()
			.categoryName(category.getCategoryName())
			.reviewCount(category.getReviewCount())
			.user(category.getUser())
			.build();

		//when
		service.addCategory(request);

		//then
		Category actual = repository.findAll().get(0);
		System.out.println(actual.getCategoryId());

	}
	@Test
	@DisplayName("카테고리 전체 조회")
	void test_카테코리_전체_조회(){
		// given
		List<Category> requestCategory = (List<Category>)IntStream.range(1, 10)
			.mapToObj(category -> Category.builder()
				.categoryName("제목 = 영화" + category)
				.reviewCount((long)category)
				.build()
			).toList();


		// when
		List<Category> category = service.findAllCategory();

		// then
		// assertEquals(2, category.size() );
	}

	@Test
	void test_카테고리_단건_조회(){
		// given
		repository.save(category);

		// when
		CategoryResponse response = service.findCategory(category.getCategoryId());
		System.out.println(category.getCategoryId());

		// then
		assertNotNull(response);
		assertEquals( "Movie", response.getCategory_name());
		assertEquals( 5L, response.getReview_count());
	}


	@Test
	void test카테고리_삭제(){
		// given
		repository.save(category);

		// when
		service.removeCategory( category.getCategoryId() );

		// then
		assertEquals( true, repository.findById(category.getCategoryId()).isEmpty() );
	}

	@Test
	void test_category_수정() throws Exception {
		// given
		repository.save(category);

		CategoryRequest categoryRequest = CategoryRequest.builder()
			.categoryName("Movie1323")
			.reviewCount(5L)
			.build();


		// when
		service.modifyCategory( category.getCategoryId(), categoryRequest );

		/// then
		Category changeCategory = repository.findById(category.getCategoryId()).orElseThrow( () -> new RuntimeException("존재 X todo ID = "+category.getCategoryId()) );


		assertEquals( "Movie1323", changeCategory.getCategoryName() );
		assertEquals( 5L, changeCategory.getReviewCount() );
	}


	}



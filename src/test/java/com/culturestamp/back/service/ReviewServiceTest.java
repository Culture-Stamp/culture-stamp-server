package com.culturestamp.back.service;

import com.culturestamp.back.controller.request.ReviewEditorRequest;
import com.culturestamp.back.controller.request.ReviewRequest;
import com.culturestamp.back.dto.ReviewResponse;
import com.culturestamp.back.entity.Category;
import com.culturestamp.back.entity.Review;
import com.culturestamp.back.entity.Role;
import com.culturestamp.back.entity.User;
import com.culturestamp.back.repository.CategoryRepository;
import com.culturestamp.back.repository.ReviewRepository;
import com.culturestamp.back.repository.UserRepository;
import com.culturestamp.back.service.impl.ReviewServiceImpl;
import com.culturestamp.back.service.impl.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.BDDAssumptions.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
public class ReviewServiceTest {
    @Autowired
    private ReviewRepository repository;

    @Autowired
    private ReviewServiceImpl service;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    private User user;
    private Review review;
    private Category category;

    @BeforeEach
    @DisplayName("user, category, review 데이터 설정")
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
        category = new Category( "Movie",2L);
        categoryRepository.save(category);

        review = Review.builder()
                .category(category)
                .price(500)
                .title("영화테스트!!")
                .content("리뷰 중 영화 리뷰 올리는 중!!")
                .companion("")
                .location("")
                .rating(5)
                .performedDate(LocalDateTime.now())
                .user(user)
                .build();
    }

    @Test
    void testMock객체생성(){
        assertNotNull(service);
    }

    @Test
    void test리뷰_기본_등록_서비스() throws Exception {
        // given

        ReviewRequest request = ReviewRequest.builder()
                                .category(review.getCategory())
                                .user(review.getUser())
                                .title(review.getTitle())
                                .performedDate(review.getPerformedDate())
                                .location(review.getLocation())
                                .companion(review.getLocation())
                                .rating(review.getRating())
                                .content(review.getContent())
                                .price(review.getPrice())
                                .build();


        // when
        service.addReview(request);

        // then
        Review actual = repository.findAll().get(0);
        // assertEquals(1L, actual.getId() );
        System.out.println(actual.getId());
    }

    @Test
    @DisplayName("글 10개까지 출력되는 1 페이지 조회 ")
    void test리뷰_전체_조회() {
        // given
        List<Review> requestReviews = (List<Review>) IntStream.range(1,10)
                                                                .mapToObj( review -> Review.builder()
                                                                                            .title("제목 = "+ review )
                                                                                            .content("내용 = " + review )
                                                                                            .build()
                                                                ).collect( Collectors.toList() );

        Pageable pageable = PageRequest.of( 0,10, Sort.by(Sort.Direction.DESC,"id") );

        // when
        List<ReviewResponse> reviews = service.findReviews(pageable);

        // then
        // assertEquals(3, reviews.size() );
        System.out.println(reviews.size());
    }

    @Test
    void test리뷰_단건_조회() {
        // given
        repository.save(review);

        // when
        ReviewResponse response = service.findReview(review.getId());

        // then
        assertNotNull(response);
        assertEquals( "영화테스트!!", response.getTitle());
        assertEquals( "리뷰 중 영화 리뷰 올리는 중!!", response.getContent());
    }

    @Test
    void test리뷰_수정() {
        // given
        repository.save(review);

        ReviewEditorRequest reviewEditorRequest = ReviewEditorRequest.builder()
                                                                        .category(category)
                                                                        .price(1000)
                                                                        .title("영화 테스트 제목 수정")
                                                                        .content("영화 테스트 내용 수정")
                                                                        .companion("영희")
                                                                        .location("신촌 CGV")
                                                                        .rating(3)
                                                                        .performedDate(LocalDateTime.now())
                                                                        .build();

        // when
        service.modifyReview( review.getId(), reviewEditorRequest );

        // then
        Review changeReview = repository.findById(review.getId()).orElseThrow( () -> new RuntimeException("글 존재 X. ID = "+review.getId()) );

        assertEquals( "영화 테스트 제목 수정", changeReview.getTitle() );
        assertEquals( "영화 테스트 내용 수정", changeReview.getContent() );
    }

    @Test
    void test리뷰_삭제(){
        // given
        repository.save(review);

        // when
        service.removeReview( review.getId() );

        // then
        assertEquals( true, repository.findById(review.getId()).isEmpty() );
    }

}

package com.culturestamp.back.service;

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
                .email("이메일@naver.com")
                .password("wtefsfd")
                .role(Role.USER)
                .lastLoginAt(new SimpleDateFormat("yyyyMMdd").parse("20221028"))
                .build();
        userRepository.save(user);

        category = new Category( "Movie","0");
        categoryRepository.save(category);

        review = Review.builder()
                .category(category)
                .price(500)
                .title("영화테스트")
                .content("리뷰 중 영화 리뷰 올리는 중 입니다.")
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
        //given
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


        //when
        service.addReview(request);

        //then
        Review actual = repository.findAll().get(0);
        assertEquals(1L, actual.getId() );
    }
}

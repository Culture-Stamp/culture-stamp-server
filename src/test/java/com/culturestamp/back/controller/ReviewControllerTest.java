package com.culturestamp.back.controller;

import com.culturestamp.back.controller.request.ReviewRequest;
import com.culturestamp.back.entity.Category;
import com.culturestamp.back.entity.Review;
import com.culturestamp.back.entity.Role;
import com.culturestamp.back.entity.User;
import com.culturestamp.back.repository.ReviewRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ReviewControllerTest {
    @Autowired
    private ReviewRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private User user;
    private Review review;
    private Category category;

    @BeforeEach
    @DisplayName("user, category, review 데이터 설정")
    void setup() throws ParseException {
        user = User.builder()
                .userId(1L)
                .nickname("별명")
                .email("이메일@naver.com")
                .password("wtefsfd")
                .role(Role.USER)
                .lastLoginAt(new SimpleDateFormat("yyyyMMdd").parse("20221028"))
                .build();

        category = new Category(1L,"Movie","0",user);

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
    void test리뷰_기본_등록_컨트롤러() throws Exception {
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

        String json = objectMapper.writeValueAsString(request);

        // when
        mockMvc.perform(MockMvcRequestBuilders.post("/review")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andDo(print());

        // then
        assertEquals(2, repository.count());

        Review actual = repository.findAll().get(0);
        assertEquals(1L, actual.getId());
    }
}

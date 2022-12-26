package com.culturestamp.back.repository;

import com.culturestamp.back.entity.Category;
import com.culturestamp.back.entity.Review;
import com.culturestamp.back.entity.Role;
import com.culturestamp.back.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository repository;

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
    @DisplayName("리뷰/등록 - 사진 제외한 기본 기능")
    void test리뷰_기본_등록(){
        Review actual = repository.save(review);
        assertEquals( review.getId(), actual.getId() );
    }

    @Test
    @DisplayName("리뷰/조회 - 아이디가 넘어오면 상세, 아니라면 전체 조회")
    void test리뷰_단건_OR_전체_조회() {

    }
    
    @Test
    void test리뷰_수정(){

    }

    @Test
    void test리뷰_삭제(){

    }
}

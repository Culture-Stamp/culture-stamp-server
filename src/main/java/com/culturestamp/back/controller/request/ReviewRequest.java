package com.culturestamp.back.controller.request;

import com.culturestamp.back.entity.Category;
import com.culturestamp.back.entity.Review;
import com.culturestamp.back.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PUBLIC) // 테스트를 위해 Public
public class ReviewRequest {
    private Long reviewId;
    private Category category;
    private User user;
    private String title;
    private LocalDateTime performedDate;
    private String location;
    private String companion;
    private int rating;
    private String content;
    private int price;

    public Review toEntity(ReviewRequest reviewRequest){
        return Review.builder()
                .id(reviewRequest.reviewId)
                .category(reviewRequest.category)
                .user(reviewRequest.user)
                .title(reviewRequest.title)
                .performedDate(reviewRequest.performedDate)
                .location(reviewRequest.location)
                .companion(reviewRequest.location)
                .rating(reviewRequest.rating)
                .content(reviewRequest.content)
                .price(reviewRequest.price)
                .build();
    }
}

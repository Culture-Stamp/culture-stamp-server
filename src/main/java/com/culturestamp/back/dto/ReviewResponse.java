package com.culturestamp.back.dto;

import com.culturestamp.back.controller.request.ReviewRequest;
import com.culturestamp.back.entity.Category;
import com.culturestamp.back.entity.Review;
import com.culturestamp.back.entity.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
public class ReviewResponse {
    private final Long reviewId;
    private final Category category;
    private final User user;
    private final String title;
    private final LocalDateTime performedDate;
    private final String location;
    private final String companion;
    private final int rating;
    private final String content;
    private final int price;

    @Builder
    public ReviewResponse(Review review) {
        this.reviewId = review.getId();
        this.category = review.getCategory();
        this.user = review.getUser();
        this.title = review.getTitle();
        this.performedDate = review.getPerformedDate();
        this.location = review.getLocation();
        this.companion = review.getCompanion();
        this.rating = review.getRating();
        this.content = review.getContent();
        this.price = review.getPrice();
    }

}


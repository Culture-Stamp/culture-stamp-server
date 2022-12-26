package com.culturestamp.back.dto;

import com.culturestamp.back.entity.Category;
import com.culturestamp.back.entity.Review;
import com.culturestamp.back.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
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

    @Builder
    public ReviewResponse(Long reviewId, Category category, User user, String title, LocalDateTime performedDate, String location, String companion, int rating, String content, int price) {
        this.reviewId = reviewId;
        this.category = category;
        this.user = user;
        this.title = title;
        this.performedDate = performedDate;
        this.location = location;
        this.companion = companion;
        this.rating = rating;
        this.content = content;
        this.price = price;
    }

}


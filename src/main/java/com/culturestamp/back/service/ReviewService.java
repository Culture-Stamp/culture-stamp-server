package com.culturestamp.back.service;

import com.culturestamp.back.controller.request.ReviewRequest;
import com.culturestamp.back.dto.ReviewResponse;
import com.culturestamp.back.entity.Review;

public interface ReviewService {
    ReviewResponse addReview(ReviewRequest reviewRequest);
}

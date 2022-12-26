package com.culturestamp.back.service.impl;

import com.culturestamp.back.controller.request.ReviewRequest;
import com.culturestamp.back.dto.ReviewResponse;
import com.culturestamp.back.entity.Review;
import com.culturestamp.back.repository.ReviewRepository;
import com.culturestamp.back.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    final private ReviewRepository repository;

    @Override
    public ReviewResponse addReview(ReviewRequest reviewRequest) {
        Review review = repository.save(reviewRequest.toEntity(reviewRequest));
        return new ReviewResponse(review);
    }

}

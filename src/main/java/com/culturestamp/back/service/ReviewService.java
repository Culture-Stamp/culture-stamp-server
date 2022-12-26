package com.culturestamp.back.service;

import com.culturestamp.back.controller.request.ReviewEditorRequest;
import com.culturestamp.back.controller.request.ReviewRequest;
import com.culturestamp.back.dto.ReviewResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {
    List<ReviewResponse> findReviews(Pageable pageable);
    ReviewResponse findReview(Long reviewId);
    ReviewResponse addReview(ReviewRequest reviewRequest);
    ReviewResponse modifyReview(Long reviewId, ReviewEditorRequest reviewRequest);
    void removeReview(Long reviewId);
}

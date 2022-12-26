package com.culturestamp.back.service.impl;

import com.culturestamp.back.controller.request.ReviewEditorRequest;
import com.culturestamp.back.controller.request.ReviewRequest;
import com.culturestamp.back.dto.ReviewResponse;
import com.culturestamp.back.entity.Review;
import com.culturestamp.back.repository.ReviewRepository;
import com.culturestamp.back.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    final private ReviewRepository repository;

    @Override
    public List<ReviewResponse> findReviews(Pageable pageable) {
        return repository.findAllPagingBy(pageable).stream().collect(Collectors.toList());
    }

    @Override
    public ReviewResponse addReview(ReviewRequest reviewRequest) {
        final Review review = repository.save(reviewRequest.toEntity(reviewRequest));
        return new ReviewResponse(review);
    }

    @Override
    public ReviewResponse findReview(Long reviewId) {
        final Review review = repository.findById(reviewId).orElseThrow(IllegalArgumentException::new);
        return new ReviewResponse(review);
    }

    @Override
    @Transactional
    public ReviewResponse modifyReview(Long reviewId, ReviewEditorRequest reviewRequest) {
        Optional<Review> reviewOptional = repository.findById(reviewId);
        reviewOptional.ifPresent( review -> {
            ReviewEditorRequest reviewEditorRequest = review.toEditor()
                                                            .category( reviewRequest.getCategory() )
                                                            .title( reviewRequest.getTitle() )
                                                            .performedDate( reviewRequest.getPerformedDate() )
                                                            .location( reviewRequest.getLocation() )
                                                            .companion( reviewRequest.getCompanion() )
                                                            .rating( reviewRequest.getRating() )
                                                            .content( reviewRequest.getContent() )
                                                            .price( reviewRequest.getPrice() )
                                                            .build();
            reviewOptional.get().edit( reviewEditorRequest );
        });
        return new ReviewResponse(reviewOptional.get());
    }

    @Override
    public void removeReview(Long reviewId) {
        final Review review = repository.findById(reviewId).orElseThrow(IllegalArgumentException::new);
        repository.delete(review);
    }

}

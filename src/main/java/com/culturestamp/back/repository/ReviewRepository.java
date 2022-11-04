package com.culturestamp.back.repository;

import com.culturestamp.back.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findAllPagingBy(Pageable pageable);
    Iterable<Review> findAllSortBy(Sort sort);
}

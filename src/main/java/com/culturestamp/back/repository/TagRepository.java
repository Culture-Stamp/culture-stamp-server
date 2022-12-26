package com.culturestamp.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.culturestamp.back.entity.Tag;

public interface TagRepository extends JpaRepository<Tag,Long> {
}

package com.culturestamp.back.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.culturestamp.back.dto.UserResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Long categoryId;

	@Column(name = "category_name")
	private String categoryName;

	@Column(name = "review_count")
	private Long reviewCount;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Category() {

	}

	@Builder
	public Category(Long categoryId, String categoryName, Long reviewCount, User user) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.reviewCount = reviewCount;
		this.user = user;
	}

	@Builder
	public Category(String categoryName, Long reviewCount, User user) {
		this.categoryName = categoryName;
		this.reviewCount = reviewCount;
		this.user = user;
	}

	public Category(String categoryName, Long reviewCount) {
	}


}

package com.culturestamp.back.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;

@Entity
@Getter
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tag_id")
	private Long tagId;

	@Column(name = "tag_name")
	private String tagName;

	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name="review_id")
	// private Review review; // Review로 변경 필요

	public Tag() {

	}

	public Tag(Long tagId, String tagName) {
		this.tagId = tagId;
		this.tagName = tagName;
	}

	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	// public Review getReview() {
	// 	return review;
	// }
	//
	// public void setReview(Review review) {
	// 	this.review = review;
	// }
}

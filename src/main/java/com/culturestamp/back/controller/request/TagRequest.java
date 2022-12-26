package com.culturestamp.back.controller.request;

import com.culturestamp.back.entity.Review;
import com.culturestamp.back.entity.Tag;
import com.culturestamp.back.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagRequest {
	private String tagName;
	private Review review;

	public Tag toEntity(TagRequest tagRequest){
		return Tag.builder()
			.tagName(tagRequest.tagName)
			.review(tagRequest.review)
			.build();
	}

}

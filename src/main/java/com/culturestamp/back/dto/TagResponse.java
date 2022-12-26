package com.culturestamp.back.dto;

import javax.persistence.Entity;

import com.culturestamp.back.entity.Tag;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TagResponse {

	private String tagName;

	public TagResponse(Tag tag) {
		this.tagName = tag.getTagName();
	}

}

package com.culturestamp.back.controller.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TagRequest {
	private String tagName;

	public TagRequest(String tagName) {
		this.tagName = tagName;
	}

	public String getTagName() {
		return tagName;
	}
}

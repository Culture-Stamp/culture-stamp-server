package com.culturestamp.back.service;

import java.util.List;

import com.culturestamp.back.controller.request.TagRequest;
import com.culturestamp.back.controller.request.TodoRequest;
import com.culturestamp.back.dto.TagResponse;
import com.culturestamp.back.dto.TodoResponse;
import com.culturestamp.back.entity.Tag;
import com.culturestamp.back.entity.Todo;

public interface TagService {
	TagResponse addTag(TagRequest tagRequest);
	List<Tag> findAllTag();
	TagResponse findTag(Long tagId);
	void removeTag(Long tagId);
	TagResponse modifyTag(Long tagId, TagRequest tagRequest) throws Exception;
}

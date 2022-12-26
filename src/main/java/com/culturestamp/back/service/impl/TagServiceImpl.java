package com.culturestamp.back.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.culturestamp.back.controller.request.TagRequest;
import com.culturestamp.back.dto.TagResponse;
import com.culturestamp.back.entity.Tag;
import com.culturestamp.back.entity.Todo;
import com.culturestamp.back.repository.TagRepository;
import com.culturestamp.back.service.TagService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
	final private TagRepository tagRepository;


	@Override
	public TagResponse addTag(TagRequest tagRequest){
		Tag tag = tagRepository.save(tagRequest.toEntity(tagRequest));
		return new TagResponse(tag);
	}

	@Override
	public List<Tag> findAllTag(){
		return tagRepository.findAll();
	}

	@Override
	public TagResponse findTag(Long tagId)  {
		Tag tag =  tagRepository.findById(tagId).orElseThrow(NullPointerException::new);
		return new TagResponse(tag);
	}

	@Override
	public void removeTag(Long tagId){
		tagRepository.deleteById(tagId);
	}

	@Override
	public TagResponse modifyTag(Long tagId, TagRequest tagRequest) throws Exception {
		Optional<Tag> optionalTag = tagRepository.findById(tagId);
		if (optionalTag.isEmpty()) {
			throw new EntityNotFoundException(
				"Todo not present in the database"
			);
		}
		Tag modifyTag= optionalTag.get();
		if(tagRequest.getTagName()!=null)modifyTag.setTagName(tagRequest.getTagName());
		modifyTag.setReview(tagRequest.getReview());
		Tag tag = tagRepository.save(modifyTag);
		return new TagResponse(tag);
	}


}
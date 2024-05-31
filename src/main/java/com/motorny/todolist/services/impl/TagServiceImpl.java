package com.motorny.todolist.services.impl;

import com.motorny.todolist.dto.TagDto;
import com.motorny.todolist.mappers.TagMapper;
import com.motorny.todolist.model.Tag;
import com.motorny.todolist.repositories.TagRepository;
import com.motorny.todolist.services.TagService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Override
    public TagDto findOrCreate(TagDto tagDto) {

        List<TagDto> foundTags = tagRepository.findByName(tagDto.getName());

        if (foundTags.isEmpty()) {
            Tag tag = tagMapper.toTag(tagDto);
            tagRepository.save(tag);
            return tagDto;
        } else {
            return foundTags.getFirst();
        }
    }
}

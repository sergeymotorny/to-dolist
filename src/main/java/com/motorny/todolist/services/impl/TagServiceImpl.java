package com.motorny.todolist.services.impl;

import com.motorny.todolist.dto.TagDto;
import com.motorny.todolist.mappers.TagMapper;
import com.motorny.todolist.model.Tag;
import com.motorny.todolist.repositories.TagRepository;
import com.motorny.todolist.services.TagService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Override
    public Tag findOrCreate(TagDto tagDto) {
        Optional<Tag> existedTag = tagRepository.findOptionalByName(tagDto.getName());

        if (existedTag.isEmpty()) {
            Tag tag = tagMapper.toTag(tagDto);
            tagRepository.save(tag);
            return tag;
        } else {
            return existedTag.get();
        }
    }

    // разделить метод
}

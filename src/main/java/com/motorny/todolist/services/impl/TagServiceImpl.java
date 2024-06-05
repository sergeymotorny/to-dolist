package com.motorny.todolist.services.impl;

import com.motorny.todolist.dto.TagDto;
import com.motorny.todolist.exceptions.CustomEmptyDataException;
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
        Optional<Tag> existedTag = findTag(tagDto.getName());

        return existedTag.orElseGet(() -> craeteTag(tagDto));
    }

    @Override
    public String deleteTag(Long id) {
        Tag tagForDelete = tagRepository.findById(id)
                .orElseThrow(() -> new CustomEmptyDataException("Tag not found with id " + id));

        tagRepository.delete(tagForDelete);

        return "Tag with id: " + id + " was successfully deleted";
    }

    private Optional<Tag> findTag(String tagName) {
        return tagRepository.findOptionalByName(tagName);
    }

    private Tag craeteTag(TagDto tagDto) {
        Tag tag = tagMapper.toTag(tagDto);
        return tagRepository.save(tag);
    }
}

package com.motorny.todolist.services;


import com.motorny.todolist.dto.TagDto;
import com.motorny.todolist.model.Tag;

public interface TagService {
    Tag findOrCreate(TagDto tagDto);
}

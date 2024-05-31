package com.motorny.todolist.services;


import com.motorny.todolist.dto.TagDto;

public interface TagService {
    TagDto findOrCreate(TagDto tagDto);
}

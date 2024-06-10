package com.motorny.todolist.mappers;

import com.motorny.todolist.dto.TagDto;
import com.motorny.todolist.model.Tag;
import com.motorny.todolist.model.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TagMapper {

    @Mapping(target = "todos", expression = "java(mapIds(tag.getTodos()))")
    TagDto toTagDto(Tag tag);

    @Mapping(target = "todos", ignore = true)
    Tag toTag(TagDto tagDto);

    default Set<Long> mapIds(Set<Todo> todos) {
        return todos.stream()
                .map(Todo::getId)
                .collect(Collectors.toSet());
    }
}

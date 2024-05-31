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
    @Mapping(target = "todoListId", expression = "java(mapTodos(tag.getTodoList()))")
    TagDto toTagDto(Tag tag);
    Tag toTag(TagDto tagDto);

    default Set<Long> mapTodos(Set<Todo> todos) {
        return todos.stream()
                .map(Todo::getId)
                .collect(Collectors.toSet());
    }
}

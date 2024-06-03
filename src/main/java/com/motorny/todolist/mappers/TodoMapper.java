package com.motorny.todolist.mappers;

import com.motorny.todolist.dto.TodoDto;
import com.motorny.todolist.model.Todo;
import com.motorny.todolist.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = TagMapper.class, imports = { java.util.HashSet.class})
public interface TodoMapper {

    @Mapping(source = "user.id", target = "userId")
    TodoDto toTodoDto(Todo todo);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(target = "tags", expression = "java(new HashSet())")
    Todo toTodo(TodoDto todoDto);
}

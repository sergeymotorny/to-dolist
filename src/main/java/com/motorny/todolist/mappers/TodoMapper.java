package com.motorny.todolist.mappers;

import com.motorny.todolist.dto.TodoDto;
import com.motorny.todolist.model.Todo;
import com.motorny.todolist.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.HashSet;

@Mapper(componentModel = "spring", uses = TagMapper.class, imports = { HashSet.class })
public interface TodoMapper {


    @Mapping(source = "user.id", target = "userId")
    TodoDto toTodoDto(Todo todo);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(target = "tags", expression = "java(new HashSet())")
    @Mapping(target = "user", ignore = true)
    Todo toTodo(TodoDto todoDto);

    default Todo toTodoWithUser(TodoDto todoDto, User user) {
        Todo todo = toTodo(todoDto);
        todo.setUser(user);
        return todo;
    }
}

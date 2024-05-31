package com.motorny.todolist.mappers;

import com.motorny.todolist.dto.TodoDto;
import com.motorny.todolist.model.Todo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = TagMapper.class)
public interface TodoMapper {

    TodoDto toTodoDto(Todo todo);
    Todo toTodo(TodoDto todoDto);
}

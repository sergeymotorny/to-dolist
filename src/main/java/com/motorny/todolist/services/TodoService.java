package com.motorny.todolist.services;

import com.motorny.todolist.dto.TodoDto;
import com.motorny.todolist.dto.UserDto;

import java.util.List;

public interface TodoService {
    List<TodoDto> getAllTodo();
    TodoDto getTodo(Long id);
    TodoDto createTodo(TodoDto todoDto);
    TodoDto updateTodo(TodoDto todoDto, Long id);
    String deleteTodo(Long id);
}

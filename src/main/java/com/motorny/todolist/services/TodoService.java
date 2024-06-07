package com.motorny.todolist.services;

import com.motorny.todolist.dto.TodoDto;
import com.motorny.todolist.dto.UserDto;

import java.util.List;

public interface TodoService {
    List<TodoDto> getAllTodo(Long userId);
    TodoDto getTodo(Long id);
    TodoDto createTodo(TodoDto todoDto, Long userId);
    TodoDto updateTodo(TodoDto todoDto, Long todoId);
    String deleteTodo(Long id);
}

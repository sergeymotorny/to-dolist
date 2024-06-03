package com.motorny.todolist.services.impl;

import com.motorny.todolist.dto.TagDto;
import com.motorny.todolist.dto.TodoDto;
import com.motorny.todolist.exceptions.CustomEmptyDataException;
import com.motorny.todolist.mappers.TagMapper;
import com.motorny.todolist.mappers.TodoMapper;
import com.motorny.todolist.model.Todo;
import com.motorny.todolist.model.User;
import com.motorny.todolist.repositories.TodoRepository;
import com.motorny.todolist.repositories.UserRepository;
import com.motorny.todolist.services.TagService;
import com.motorny.todolist.services.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;
    private final TodoMapper todoMapper;
    private final TagService tagService;
    private final TagMapper tagMapper;

    @Override
    public List<TodoDto> getAllTodo(Long userId) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            return todoRepository.findAllByUser(user.get()).stream()
                    .map(todoMapper::toTodoDto)
                    .collect(Collectors.toList());
        } else {
            throw new CustomEmptyDataException("User with id: " + userId + " not found");
        }

    }

    @Override
    public TodoDto getTodo(Long id) {
        Todo byId = todoRepository.findById(id)
                .orElseThrow(() -> new CustomEmptyDataException("Todo not found with id " + id));

        return todoMapper.toTodoDto(byId);
    }

    @Transactional
    @Override
    public TodoDto createTodo(TodoDto todoDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomEmptyDataException("Unable to get user for todo"));

        Todo todo = todoMapper.toTodo(todoDto);
        todo.setUser(user); //!

        todoDto.getTags().stream()
                .map(tagService::findOrCreate)
                .collect(Collectors.toSet())
                .forEach(tag -> todo.addTag(tag));

        Todo saveTodo = todoRepository.save(todo);

        //tagsDto.stream()
        //.map(tagService::findOrCreate)
        //.map(tagMapper::toTag)
        //.forEach(saveTodo::addTag);

        return todoMapper.toTodoDto(saveTodo);
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long todoId) {
        Todo existingTodo = todoRepository.findById(todoId)
                .orElseThrow(() -> new CustomEmptyDataException("Todo not found with id " + todoId));

        existingTodo.setName(todoDto.getName());
        existingTodo.setComment(todoDto.getComment());
        existingTodo.setStartDate(todoDto.getStartDate());
        existingTodo.setEndDate(todoDto.getEndDate());
        existingTodo.setImportant(todoDto.getImportant());
        existingTodo.setPriority(todoDto.getPriority());

        todoRepository.save(existingTodo);

        return todoMapper.toTodoDto(existingTodo);
    }

    @Override
    public String deleteTodo(Long id) {
        Todo todoForDelete = todoRepository.findById(id)
                .orElseThrow(() -> new CustomEmptyDataException("Todo not found with id " + id));

        todoForDelete.getTags()
                 .forEach(tag -> tag.removeTodo(todoForDelete));

        todoRepository.delete(todoForDelete);

        return "Todo with id " + id + " successfully deleted";
    }
}

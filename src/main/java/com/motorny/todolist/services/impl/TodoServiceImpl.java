package com.motorny.todolist.services.impl;

import com.motorny.todolist.dto.TodoDto;
import com.motorny.todolist.exceptions.CustomEmptyDataException;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;
    private final TodoMapper todoMapper;
    private final TagService tagService;

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

        Todo todo = todoMapper.toTodoWithUser(todoDto, user);

        todoDto.getTags().stream()
                .map(tagService::findOrCreate)
                .collect(Collectors.toSet())
                .forEach(todo::addTag);

        Todo savedTodo = todoRepository.save(todo);

        return todoMapper.toTodoDto(savedTodo);
    }

    @Transactional
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

    @Transactional
    @Override
    public String deleteTodo(Long id) {
        Todo todoForDelete = todoRepository.findById(id)
                .orElseThrow(() -> new CustomEmptyDataException("Todo not found with id " + id));

        todoForDelete.removeTags();

        todoRepository.delete(todoForDelete);

        return "Todo with id " + id + " was successfully deleted";
    }
}

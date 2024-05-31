package com.motorny.todolist.services.impl;

import com.motorny.todolist.dto.TagDto;
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

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;
    private final TagService tagService;

    @Override
    public List<TodoDto> getAllTodo() {
        List<Todo> todoList = todoRepository.findAll();

        if (!todoList.isEmpty()) {
            return todoList.stream()
                    .map(todoMapper::toTodoDto)
                    .collect(Collectors.toList());
        } else {
            throw new CustomEmptyDataException("No todos found");
        }
    }

    @Override
    public TodoDto getTodo(Long id) {
        Todo byId = todoRepository.findById(id)
                .orElseThrow(() -> new CustomEmptyDataException("Todo not found with id " + id));

        return todoMapper.toTodoDto(byId);
    }

    @Override
    public TodoDto createTodo(TodoDto todoDto) {
        Todo todo = todoMapper.toTodo(todoDto);

        if (todo != null) {
            Set<TagDto> tags = new HashSet<>(todoDto.getTagDtoSet());

            todoDto.getTagDtoSet().clear();

            todo.setUser(todo.getUser());

            Todo saveTodo = todoRepository.save(todo);

            tags.stream()
                    .map(tagService::findOrCreate)
                    .collect(Collectors.toSet())
                    .forEach(todo.addTag());


        }

        return todoMapper.toTodoDto(saveTodo);
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long id) {
        Todo existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new CustomEmptyDataException("Todo not found with id " + id));

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
        Todo existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new CustomEmptyDataException("Todo not found with id " + id));

        existingTodo.getSetList()
                .forEach(tag -> tag.removeTodo(existingTodo));

        todoRepository.delete(existingTodo);

        return "Todo with id " + id + " successfully deleted";
    }
}

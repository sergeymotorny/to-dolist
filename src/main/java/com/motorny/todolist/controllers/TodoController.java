package com.motorny.todolist.controllers;

import com.motorny.todolist.dto.TodoDto;
import com.motorny.todolist.services.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/t")
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/user/{userId}/todos")
    public ResponseEntity<List<TodoDto>> getAllTodo(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(todoService.getAllTodo(userId), HttpStatus.OK);
    }

    @GetMapping("/user/todo/{todoId}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable("todoId") Long todoId) {
        return new ResponseEntity<>(todoService.getTodo(todoId), HttpStatus.OK);
    }

    @PostMapping("/user/{userId}/todo")
    public ResponseEntity<TodoDto> createTodo(@RequestBody TodoDto todoDto, @PathVariable("userId") Long userId) {
        return new ResponseEntity<>(todoService.createTodo(todoDto, userId), HttpStatus.CREATED);
    }

    @PutMapping("/user/todo/{todoId}")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto, @PathVariable("todoId") Long todoId) {
        return new ResponseEntity<>(todoService.updateTodo(todoDto, todoId), HttpStatus.OK);
    }

    @DeleteMapping("/user/todo/{todoId}")
    public ResponseEntity<String> deleteTodo(@PathVariable("todoId") Long todoId) {
        return new ResponseEntity<>(todoService.deleteTodo(todoId), HttpStatus.OK);
    }
}

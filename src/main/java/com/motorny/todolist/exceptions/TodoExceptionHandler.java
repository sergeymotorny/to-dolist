package com.motorny.todolist.exceptions;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class TodoExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> onMissingTodo(NoSuchElementException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ClassUtils.getShortName(exception.getClass())
                        + ": No such user was found");
    }

    @ExceptionHandler
    public ResponseEntity<String> onMissingTodo(EmptyResultDataAccessException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ClassUtils.getShortName(exception.getClass())
                        + exception.getLocalizedMessage()
                        + ": No one todo was found");
    }

    @ExceptionHandler
    public ResponseEntity<String> SQLProblems(SQLException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ClassUtils.getShortName(exception.getClass())
                        + exception.getLocalizedMessage()
                        + ": something went wrong with todo");
    }

    @ExceptionHandler
    public ResponseEntity<String> customExceptionHandler(CustomEmptyDataException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ClassUtils.getShortName(exception.getClass())
                        + " "
                        + exception.getCause()
                        + " "
                        + exception.getLocalizedMessage());
    }
}

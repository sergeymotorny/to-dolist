package com.motorny.todolist.dto;

import com.motorny.todolist.model.Todo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String email;
    private String password;
    private Set<TodoDto> todoDtoSet;
}

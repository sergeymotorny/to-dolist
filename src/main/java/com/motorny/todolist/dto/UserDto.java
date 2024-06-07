package com.motorny.todolist.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserDto {

    private Long id;
    private String email;
    private String password;
    private Set<TodoDto> todos;
}

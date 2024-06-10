package com.motorny.todolist.dto;

import com.motorny.todolist.validators.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserDto {

    private Long id;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid", regexp = "^[A-Za-z0-9+_.-]+@gmail.com")
    private String email;

    @NotNull
    @ValidPassword
    private String password;

    private Set<TodoDto> todos;


}

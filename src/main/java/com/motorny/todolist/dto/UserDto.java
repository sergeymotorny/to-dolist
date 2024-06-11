package com.motorny.todolist.dto;

import com.motorny.todolist.validators.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserDto {

    private Long id;

    @NotBlank(message = "Email cannot be blank")
    @Email(
            regexp = "^[A-Za-z0-9+_.-]+@(gmail|urk|meta|online|zoho|yahoo)\\.(com|net|ua|mail)",
            message = "Invalid email address. It should be a valid email from the following domains: " +
                        "gmail, urk, meta, online, zoho, yahoo.")
    private String email;

    @ValidPassword
    private String password;

    private Set<TodoDto> todos;
}

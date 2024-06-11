package com.motorny.todolist.controllers;

import com.motorny.todolist.dto.UserDto;
import com.motorny.todolist.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/u")
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        return userService.getAllUser();
    }

    @GetMapping("/user/{id}")
    public UserDto getUser(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }

    @PostMapping("/user")
    @ResponseStatus(value = HttpStatus.CREATED)
    public UserDto createUser(@Valid @RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PutMapping("/user/{id}")
    public UserDto updateUser(@Valid @RequestBody UserDto userDto,
                              @PathVariable("id") Long id) {
        return userService.updateUser(userDto, id);
    }

    @DeleteMapping("/user/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteUser(@PathVariable("id") Long id) {
        return userService.deleteUser(id);
    }
}

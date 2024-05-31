package com.motorny.todolist.services;

import com.motorny.todolist.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUser();
    UserDto getUser(Long id);
    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user, Long id);
    String deleteUser(Long id);
}

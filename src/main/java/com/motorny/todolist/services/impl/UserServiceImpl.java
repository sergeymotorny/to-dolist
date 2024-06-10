package com.motorny.todolist.services.impl;

import com.motorny.todolist.dto.UserDto;
import com.motorny.todolist.exceptions.CustomEmptyDataException;
import com.motorny.todolist.mappers.UserMapper;
import com.motorny.todolist.model.User;
import com.motorny.todolist.repositories.UserRepository;
import com.motorny.todolist.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDto> getAllUser() {
        List<User> userList = userRepository.findAll();

        if (!userList.isEmpty()) {
            return userList.stream()
                    .map(userMapper::toUserDto)
                    .collect(Collectors.toList());
        } else {
            throw new CustomEmptyDataException("No users found");
        }
    }

    @Override
    public UserDto getUser(Long id) {
        User byId = userRepository.findById(id)
                .orElseThrow(() -> new CustomEmptyDataException("User not found with id " + id));

        return userMapper.toUserDto(byId);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.toUser(userDto);

        User saveUser = userRepository.save(user);

        return userMapper.toUserDto(saveUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new CustomEmptyDataException("User not found with id " + id));

        existingUser.setEmail(userDto.getEmail());
        existingUser.setPassword(userDto.getPassword());

        userRepository.save(existingUser);

        return userMapper.toUserDto(existingUser);
    }

    @Override
    public String deleteUser(Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new CustomEmptyDataException("User not found with id " + id));

        userRepository.delete(existingUser);

        return "User with id: " + id + " was successfully deleted";
    }
}

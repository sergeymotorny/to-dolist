package com.motorny.todolist.mappers;

import com.motorny.todolist.dto.UserDto;
import com.motorny.todolist.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = TodoMapper.class)
public interface UserMapper {

    UserDto toUserDto(User user);
    User toUser(UserDto userDto);
}

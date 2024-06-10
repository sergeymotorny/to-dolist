package com.motorny.todolist.dto;

import com.motorny.todolist.validators.ValidTagName;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class TagDto {

    private Long id;

    @ValidTagName
    private String name;

    private Set<Long> todos;
}

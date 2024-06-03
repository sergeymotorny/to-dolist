package com.motorny.todolist.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class TagDto {

    private Long id;
    private String name;
    private Set<Long> todos;
}

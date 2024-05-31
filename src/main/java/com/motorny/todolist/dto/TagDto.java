package com.motorny.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class TagDto {

    private Long id;
    private String name;
    private Set<Long> todoListId;
}

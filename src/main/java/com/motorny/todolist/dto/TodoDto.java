package com.motorny.todolist.dto;

import com.motorny.todolist.model.Priority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class TodoDto {

    private Long id;
    private String name;
    private String comment;
    private Date startDate;
    private Date endDate;
    private Boolean important;
    private Priority priority;
    private Set<TagDto> tagDtoSet;
    private Long userId;
}

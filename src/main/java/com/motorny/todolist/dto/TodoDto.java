package com.motorny.todolist.dto;

import com.motorny.todolist.model.Priority;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Data
@Builder
public class TodoDto {

    private Long id;
    private String name;
    private String comment;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean important;
    private Priority priority;
    private Set<TagDto> tags;
    private Long userId;
}

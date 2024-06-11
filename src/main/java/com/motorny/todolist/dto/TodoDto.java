package com.motorny.todolist.dto;

import com.motorny.todolist.model.Priority;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
public class TodoDto {

    private Long id;

    @Size(min = 3, max = 50, message = "Invalid length")
    @NotBlank(message = "Todo name cannot be blank")
    private String name;

    @Pattern(regexp = "[A-Za-z0-9!?@#$%^&*()\\s]+")
    private String comment;

    @FutureOrPresent(message = "Start date must be today or in the future")
    private LocalDate startDate;

    @Future(message = "End date must be in the future")
    private LocalDate endDate;

    @NotNull(message = "Todo importance cannot be null")
    private Boolean important;

    @NotNull(message = "Todo priority is required")
    private Priority priority;

    @UniqueElements(message = "Tags should be unique")
    private Set<TagDto> tags;

    private Long userId;
}

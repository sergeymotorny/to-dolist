package com.motorny.todolist.repositories;

import com.motorny.todolist.dto.TagDto;
import com.motorny.todolist.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<TagDto> findByName(String name);
}

package com.motorny.todolist.repositories;

import com.motorny.todolist.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findOptionalByName(String name);
}

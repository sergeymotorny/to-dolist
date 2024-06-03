package com.motorny.todolist.repositories;

import com.motorny.todolist.model.Todo;
import com.motorny.todolist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByUser(User user);
}

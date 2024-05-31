package com.motorny.todolist.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "tagList")
    private Set<Todo> todoList = new HashSet<>();

    public void addTodo(Todo todo) {
        addTodo(todo, false);
    }

    public void addTodo(Todo todo, boolean otherSideHasBenSet) {
        this.getTodoList().add(todo);
        if (otherSideHasBenSet) {
            return;
        }
        todo.addTag(this, true);
    }

    public void removeTodo(Todo todo) {
        removeTodo(todo, false);
    }

    public void removeTodo(Todo todo, boolean otherSideHasBenSet) {
        this.getTodoList().remove(todo);
        if (otherSideHasBenSet) {
            return;
        }
        todo.removeTag(this, true);
    }

//    public void addTodo(Todo todo) {
//        this.todoList.add(todo);
//        todo.addTag(this, true);
//    }
//
//    public void removeTodo(Todo todo) {
//        this.todoList.remove(todo);
//        todo.removeTag(this, true);
//    }
}

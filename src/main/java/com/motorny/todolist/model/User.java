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
@Table(name = "_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Todo> todoList = new HashSet<>();

    public void addTodo(Todo todo) {
        addTodo(todo, false);
    }

    public void addTodo(Todo todo, boolean otherSideHasBenSet) {
        getTodoList().add(todo);
        if (otherSideHasBenSet) {
            return;
        }
        todo.setUser(this, true);
    }

    public void removeTodo(Todo todo) {
        removeTodo(todo, false);
    }

    public void removeTodo(Todo todo, boolean otherSideHasBenSet) {
        this.getTodoList().remove(todo);
        if (otherSideHasBenSet) {
            return;
        }
        todo.removeUser(this, true);
    }

//    public void addTodo(Todo todo) {
//        this.todoList.add(todo);
//        todo.setUser(this, true);
//    }
//
//    //when we call removeTodo on a user, the reference to the user in the todo.user task will be removed
//    public void removeTodo(Todo todo, boolean otherSideHasBeenSet) {
//        this.todoList.remove(todo);
//        todo.setUser(null, true);
//    }
}

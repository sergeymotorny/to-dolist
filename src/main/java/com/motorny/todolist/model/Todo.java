package com.motorny.todolist.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "comment")
    private String comment;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "important", nullable = false)
    private Boolean important;

    @Column(name = "priority", nullable = false)
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @ManyToMany
    @JoinTable(
            name = "todo_tag",
            joinColumns = @JoinColumn(name = "todo_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public void setUser(User user) {
        this.user = user;
        user.addTodo(this);
    }

    public void removeUser(User user) {
        this.user = null;
        user.getTodos().remove(this);
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
        tag.addTodo(this);
    }

    public void removeTag(Tag tag) {
        this.tags.remove(tag);
        tag.setTodos(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return Objects.equals(id, todo.id) && Objects.equals(name, todo.name)
                && Objects.equals(comment, todo.comment)
                && Objects.equals(startDate, todo.startDate)
                && Objects.equals(endDate, todo.endDate)
                && Objects.equals(important, todo.important)
                && priority == todo.priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, comment, startDate, endDate, important, priority);
    }
}

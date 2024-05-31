package com.motorny.todolist.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
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
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

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
    private Set<Tag> tagSet = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public void setUser(User user) {
        setUser(user, false);
    }

    public void setUser(User user, boolean otherSideHasBenSet) {
        this.user = user;
        if (otherSideHasBenSet) {
            return;
        }
        user.addTodo(this, true);
    }

    public void removeUser(User user) {
        removeUser(user, false);
    }

    public void removeUser(User user, boolean otherSideHasBenSet) {
        this.user = null;
        if (otherSideHasBenSet) {
            return;
        }
        user.removeTodo(this, true);
    }


    public Set<Tag> getSetList() {
        return tagSet;
    }

    public void addTag(Tag tag) {
        addTag(tag, false);
    }

    public void addTag(Tag tag, boolean otherSideHasBenSet) {
        this.getSetList().add(tag);
        if (otherSideHasBenSet) {
            return;
        }
        tag.addTodo(this, true);
    }

    public void removeTag(Tag tag) {
        removeTag(tag, false);
    }

    public void removeTag(Tag tag, boolean otherSideHasBenSet) {
        this.getSetList().remove(tag);
        if (otherSideHasBenSet) {
            return;
        }
        tag.removeTodo(this, true);
    }



//    public void setUser(User user, boolean otherSideHasBeenSet) {
//        this.user = user;
//        if (!otherSideHasBeenSet && user != null) {
//            user.addTodo(this);
//        }
//    }
//
//    // when removeUser is called on a task, the user reference todo.user will be reset
//    public void removeUser(User user, boolean otherSideHasBeenSet) {
//        this.user = null;
//        user.removeTodo(this, true);
//    }
//
//    public void addTag(Tag tag, boolean otherSideHasBeenSet) {
//        this.tagList.add(tag);
//        if (!otherSideHasBeenSet && tag != null) {
//            tag.getTodoList().add(this);
//        }
//    }
//
//    public void removeTag(Tag tag) {
//        this.tagList.remove(tag);
//        tag.getTodoList().remove(this);
//    }
}

package com.motorny.todolist.controllers;

import com.motorny.todolist.services.TagService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TagController {

    private final TagService tagService;

    @DeleteMapping("/tag/{tagId}")
    public ResponseEntity<String> deleteTag(@PathVariable("tagId") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(tagService.deleteTag(id));
    }
}

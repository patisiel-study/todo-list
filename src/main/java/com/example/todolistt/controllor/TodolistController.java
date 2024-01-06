package com.example.todolistt.controllor;


//import org.springframework.web.bind.annotation.RequestBody;
import com.example.todolistt.domain.Todolist;
import com.example.todolistt.dto.TodolistDto;
import com.example.todolistt.service.TodolistService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todolist")
public class TodolistController {

    private final TodolistService todolistService;

    public TodolistController(TodolistService todolistService) {
        this.todolistService = todolistService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<TodolistDto>> getAllTodolists() {
        List<TodolistDto> todolistDtoList = todolistService.getAllTodolists();
        if (todolistDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(todolistDtoList, HttpStatus.OK);
        }
    }



    @PostMapping("/create")
    public ResponseEntity<Todolist> createTodolist(@RequestBody TodolistDto todolistDto) {
        Todolist createdTodolist = todolistService.createTodolist(todolistDto);
        return new ResponseEntity<>(createdTodolist, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTodolist(@PathVariable Long id, @RequestBody TodolistDto todolistDto) {
        try {
            Todolist updatedTodolist = todolistService.updateTodolist(id, todolistDto);
            return ResponseEntity.ok(updatedTodolist);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodolist(@PathVariable Long id) {
        try {
            todolistService.deleteTodolist(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

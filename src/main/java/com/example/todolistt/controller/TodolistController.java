package com.example.todolistt.controller;


//import org.springframework.web.bind.annotation.RequestBody;
import com.example.todolistt.domain.Todolist;
import com.example.todolistt.dto.TodolistDto;
import com.example.todolistt.repository.TodolistRepository;
import com.example.todolistt.service.TodolistService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todolist")
public class TodolistController {

    private final TodolistService todolistService;

    @Autowired
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
        try {
            Todolist createdTodolist = todolistService.createTodolist(todolistDto);
            return new ResponseEntity<>(createdTodolist, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTodolist(@PathVariable Long id, @RequestBody TodolistDto todolistDto) {
        try {
            todolistService.updateTodolist(id, todolistDto);
            return ResponseEntity.ok("일정 업데이트 성공");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("오류 발생");
        }
    }

    @DeleteMapping("/{id}")  //delete
    public ResponseEntity<String> deleteTodolistById(@PathVariable Long id) {
        try {
            todolistService.deleteTodolist(id);
            return new ResponseEntity<>("일정 삭제 성공", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("오류 발생", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
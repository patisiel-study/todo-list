package com.example.todolistc.Controllor;

import com.example.todolistc.Service.TodolistService;
import com.example.todolistc.dto.TodolistDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todolist")
public class TodolistControllor {
    private final TodolistService todolistService;

    @Autowired
    public TodolistControllor(TodolistService todolistService) {
        this.todolistService = todolistService;
    }

    @GetMapping("/list")
    public List<TodolistDto> getTodolists() {
        return todolistService.getTodolists();
    }

    @GetMapping("/{id}")
    public TodolistDto getTodolist(@PathVariable Long id) {
        return todolistService.getTodolist(id);
    }

    @PostMapping("/create")
    public TodolistDto createTodolist(@RequestBody TodolistDto todolistDto) {
        return todolistService.createTodolist(todolistDto);
    }

    @PutMapping("/{id}")
    public TodolistDto updateTodolist(@PathVariable Long id, @RequestBody TodolistDto todolistDto) {
        return todolistService.updateTodolist(id, todolistDto);
    }

    @DeleteMapping("/{id}")
    public void deleteTodolist(@PathVariable Long id) {
        todolistService.deleteTodolist(id);
    }
}

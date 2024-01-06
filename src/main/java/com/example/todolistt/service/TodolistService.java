package com.example.todolistt.service;

import com.example.todolistt.dto.TodolistDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.todolistt.domain.Todolist;
import com.example.todolistt.repository.TodolistRepository;

@Service
public class TodolistService {

    private final TodolistRepository todolistRepository;

    public TodolistService(TodolistRepository todolistRepository) {
        this.todolistRepository = todolistRepository;
    }

    public List<TodolistDto> getAllTodolists() {
        List<Todolist> todolists = todolistRepository.findAll();
        return todolists.stream()
                .map(todolist -> new TodolistDto(todolist.getId(), todolist.getContent(), todolist.getDate()))
                .collect(Collectors.toList());
    }

    public Todolist createTodolist(TodolistDto todolistDto) {
        Todolist todolist = new Todolist();
        todolist.setContent(todolistDto.getContent());
        todolist.setDate(todolistDto.getDate());
        return todolistRepository.save(todolist);
    }

    public Todolist updateTodolist(Long id, TodolistDto todolistDto) {
        return todolistRepository.findById(id)
                .map(existingTodolist -> {
                    existingTodolist.setContent(todolistDto.getContent());
                    existingTodolist.setDate(todolistDto.getDate());
                    return todolistRepository.save(existingTodolist);
                }).orElseThrow(() -> new EntityNotFoundException("Todolist not found with id " + id));
    }

    public void deleteTodolist(Long id) {
        todolistRepository.deleteById(id);
    }
}

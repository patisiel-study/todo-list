package com.example.todolistc.Service;

import com.example.todolistc.domain.Todolist;
import com.example.todolistc.dto.TodolistDto;
import com.example.todolistc.repository.TodolistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodolistService {

    private final TodolistRepository todolistRepository;

    @Autowired
    public TodolistService(TodolistRepository todolistRepository) {
        this.todolistRepository = todolistRepository;
    }

    public List<TodolistDto> getTodolists() {
        return todolistRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public TodolistDto getTodolist(Long id) {
        Todolist todolist = todolistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid todolist Id:" + id));
        return convertToDto(todolist);
    }

    public TodolistDto createTodolist(TodolistDto todolistDto) {
        Todolist todolist = convertToEntity(todolistDto);
        return convertToDto(todolistRepository.save(todolist));
    }

    public TodolistDto updateTodolist(Long id, TodolistDto todolistDto) {
        Todolist todolist = todolistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid todolist Id:" + id));
        todolist.setContent(todolistDto.getContent());
        todolist.setDate(todolistDto.getDate());
        return convertToDto(todolistRepository.save(todolist));
    }

    public void deleteTodolist(Long id) {
        Todolist todolist = todolistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid todolist Id:" + id));
        todolistRepository.delete(todolist);
    }

    private TodolistDto convertToDto(Todolist todolist) {
        TodolistDto dto = new TodolistDto();
        dto.setId(todolist.getId());
        dto.setContent(todolist.getContent());
        dto.setDate(todolist.getDate());
        return dto;
    }

    private Todolist convertToEntity(TodolistDto dto) {
        Todolist todolist = new Todolist();
        todolist.setId(dto.getId());
        todolist.setContent(dto.getContent());
        todolist.setDate(dto.getDate());
        return todolist;
    }
}
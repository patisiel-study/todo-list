package com.example.todolistt.service;

import com.example.todolistt.dto.TodolistDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.todolistt.domain.Todolist;
import com.example.todolistt.repository.TodolistRepository;
@Service
public class TodolistService {

    private final TodolistRepository todolistRepository;

    @Autowired
    public TodolistService(TodolistRepository todolistRepository) {
        this.todolistRepository = todolistRepository;
    }

    public List<TodolistDto> getAllTodolists() { //수정
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
        Todolist todolist = todolistRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 데이터 없음"));
        todolist.setContent(todolistDto.getContent());
        todolist.setDate(todolistDto.getDate());
        return todolistRepository.save(todolist);
    }//update

    public void deleteTodolist(Long id) {
        if (!todolistRepository.existsById(id)) {
            throw new EntityNotFoundException("해당 데이터 없음");
        }
        todolistRepository.deleteById(id);
    }
}
package com.example.todolistt.repository;

import com.example.todolistt.domain.Todolist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodolistRepository extends JpaRepository<Todolist, Long> {
    Optional<Todolist> findByContent(String Content);

}
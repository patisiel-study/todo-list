package com.example.todolistc.repository;


import com.example.todolistc.domain.Todolist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodolistRepository extends JpaRepository<Todolist, Long> {
}

//repository

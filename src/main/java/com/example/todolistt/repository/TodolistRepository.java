package com.example.todolistt.repository;

import com.example.todolistt.domain.Todolist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodolistRepository extends JpaRepository<Todolist, Long> {

}
package com.example.todolistt.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class TodolistDto {

    private Long id;
    private String content;
    private String date;

    public TodolistDto(Long id, String content, String date) {
        this.id = id;
        this.content = content;
        this.date = date;
    }

}

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
    private boolean checked;

    public TodolistDto(Long id, String content, String date, boolean checked) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.checked = checked;
    }

}

package com.example.todolistt.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class TodolistDto {

    private Long id;
    private String content;
    private boolean checked;

    public TodolistDto(Long id, String content, boolean checked) {
        this.id = id;
        this.content = content;
        this.checked = checked;
    }

}

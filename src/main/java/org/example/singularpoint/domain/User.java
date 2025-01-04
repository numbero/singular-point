package org.example.singularpoint.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class User {
    private Long id;
    private String name;
    private String email;
    private int age;
    private LocalDate birthDay;
}

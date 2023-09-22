package com.example.demo.dto;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table (name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentName;
    private String studentAge;
    private String studentGrade;
    private String studentAddress;
    // Other fields and getters/setters
}
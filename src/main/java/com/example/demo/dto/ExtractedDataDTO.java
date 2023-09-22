package com.example.demo.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter; // Import Lombok's Getter and Setter annotations

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtractedDataDTO {
    @Id
    private String co_id;

    @Column(columnDefinition = "VARCHAR(255)")
    private String author_name;

    @Column(columnDefinition = "VARCHAR(255)")
    private String book_name;

    @Column(columnDefinition = "VARCHAR(255)")
    private String co_id_with_quotes;

    @Column(columnDefinition = "VARCHAR(255)")
    private String phone;

    @Column // Add a column for the new field
    private Integer mobile;
}


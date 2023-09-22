package com.example.demo.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtractedDataDTO {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String co_id;

    @Column(columnDefinition = "VARCHAR(255)")
    private String author_name;

    @Column(columnDefinition = "VARCHAR(255)")
    private String book_name;

    @Column(columnDefinition = "VARCHAR(255)")
    private String co_id_with_quotes;

    @Column(columnDefinition = "VARCHAR(255)")
    private String phone;
}

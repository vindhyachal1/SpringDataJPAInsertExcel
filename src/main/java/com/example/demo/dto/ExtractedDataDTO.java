package com.example.demo.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtractedDataDTO {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer co_id; // Change the type of co_id to Integer

    @Column(columnDefinition = "VARCHAR(255)")
    private String author_name;

    @Column(columnDefinition = "VARCHAR(255)")
    private String book_name;

    @Column(columnDefinition = "VARCHAR(255)")
    private String co_id_with_quotes;

    @Column(columnDefinition = "VARCHAR(255)")
    private String phone;

    @ElementCollection
    private List<Integer> mobile;

//    public void setCo_idList(List<Integer> coIdList) {
//    }
    public void setCo_id(Integer co_id) {
        this.co_id = co_id;
    }

    public void setCo_idList(List<Integer> coIdList) {
    }
}

package com.example.demo.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
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

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "insertion_time")
    private Date insertionTime;

    public void setCo_idList(List<Integer> coIdList) {
    }
}

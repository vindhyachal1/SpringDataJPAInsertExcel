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
@Table(name = "data")
public class ExtractedDataDTO {
    @Id
    private Integer co_id;
    private String author_name;
    private String book_name;
    private String co_id_with_quotes;
    private String phone;
    private List<Integer> mobile;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "insertion_time")
    private Date insertionTime;

    public void setCo_idList(List<Integer> coIdList) {
    }
}

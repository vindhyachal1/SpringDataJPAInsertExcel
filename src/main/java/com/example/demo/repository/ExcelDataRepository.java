package com.example.demo.repository;

import com.example.demo.dto.ExtractedDataDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExcelDataRepository extends JpaRepository<ExtractedDataDTO, Long> {
}


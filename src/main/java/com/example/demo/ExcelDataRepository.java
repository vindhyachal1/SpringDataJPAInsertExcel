package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExcelDataRepository extends JpaRepository<ExtractedDataDTO, Long> {
}


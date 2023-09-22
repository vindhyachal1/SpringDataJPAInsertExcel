package com.example.demo.service;

import com.example.demo.dto.ExtractedDataDTO;
import com.example.demo.repository.ExcelDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExcelDataService {
    @Autowired
    private ExcelDataRepository excelDataRepository;

    public void save(ExtractedDataDTO data) {
        excelDataRepository.save(data);
    }
}


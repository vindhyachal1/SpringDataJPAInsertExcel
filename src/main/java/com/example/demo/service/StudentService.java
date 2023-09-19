package com.example.demo.service;

import com.example.demo.ExcelDataRepository;
import com.example.demo.dto.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepository excelDataRepository;

    public void save(Student data) {
        excelDataRepository.save(data);
    }
}

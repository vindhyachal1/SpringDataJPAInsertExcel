package com.example.demo.repository;

import com.example.demo.dto.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // You can define custom query methods here if needed
}


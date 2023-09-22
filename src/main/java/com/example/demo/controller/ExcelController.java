package com.example.demo.controller;

import com.example.demo.service.ExcelDataService;
import com.example.demo.dto.ExtractedDataDTO;
import com.example.demo.dto.Student;
import com.example.demo.reader.ExcelDataReaderService;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/excel")
public class ExcelController {
    @Autowired
    private ExcelDataReaderService excelDataReaderService;

    @Autowired
    private ExcelDataService excelDataService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/extractDataAndInsert")
    public ExtractedDataDTO extractDataAndInsert() {
        Map<String, List<String>> extractedData = excelDataReaderService.extractDataFromExcel("src/main/resources/books.xlsx");
        ExtractedDataDTO dto = new ExtractedDataDTO();

        List<String> co_id = extractedData.get("Column 1");
        List<String> phone_id = extractedData.get("Column 2");
        List<String> book_name = extractedData.get("Column 3");
        List<String> author_name = extractedData.get("Column 4");

        // Insert data into H2 database
        for (int i = 0; i < co_id.size(); i++) {
            ExtractedDataDTO data = new ExtractedDataDTO();
            data.setCo_id("'" + co_id.get(i) + "'"); // Wrap with single quotes
            data.setPhone("'" + phone_id.get(i) + "'"); // Wrap with single quotes
            data.setBook_name("'" + book_name.get(i) + "'");
            data.setAuthor_name("'" + author_name.get(i) + "'");
            excelDataService.save(data);
        }

        dto.setCo_id(String.join(", ", co_id));
        dto.setPhone(String.join(", ", phone_id));
        dto.setBook_name(String.join(", ", book_name));
        dto.setAuthor_name(String.join(", ", author_name));
        dto.setCo_id_with_quotes(co_id.stream().map(value -> "'" + value + "'").collect(Collectors.joining(", ")));

        System.out.println("CID: " + dto.getCo_id());
        System.out.println("PHONE: " + dto.getPhone());
        System.out.println("BOOK: " + dto.getBook_name());
        System.out.println("AUTHOR: " + dto.getAuthor_name());
        System.out.println("CID with quotes: " + dto.getCo_id_with_quotes());

        ///////////////////////////////////
        Student student = new Student();

        List<String> studentName = extractedData.get("Column 5");
        List<String> studentAge = extractedData.get("Column 6");
        List<String> studentGrade = extractedData.get("Column 7");
        List<String> studentAddress = extractedData.get("Column 8");

        // Insert data into H2 database
        for (int i = 0; i < co_id.size(); i++) {
            Student data = new Student();
            data.setStudentName(studentName.get(i));
            data.setStudentAge(studentAge.get(i));
            data.setStudentGrade(studentGrade.get(i));
            data.setStudentAddress(studentAddress.get(i));
            studentService.save(data);
        }

        student.setStudentName(String.join(", ", studentName));
        student.setStudentAge(String.join(", ", studentAge));
        student.setStudentGrade(String.join(", ", studentGrade));
        student.setStudentAddress(String.join(", ", studentAddress));
//        dto.setCo_id_with_quotes(co_id.stream().map(value -> "'" + value + "'").collect(Collectors.joining(", ")));

        System.out.println("NAME: " + dto.getCo_id());
        System.out.println("AGE: " + dto.getPhone());
        System.out.println("GRADE: " + dto.getBook_name());
        System.out.println("ADDRESS: " + dto.getAuthor_name());
//        System.out.println("CID with quotes: " + dto.getCo_id_with_quotes());

        return dto;
    }
}
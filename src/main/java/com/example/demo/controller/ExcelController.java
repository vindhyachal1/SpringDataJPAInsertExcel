package com.example.demo.controller;

import com.example.demo.dto.ExtractedDataDTO;
import com.example.demo.dto.Student;
import com.example.demo.reader.ExcelDataReaderService;
import com.example.demo.service.ExcelDataService;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
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
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/iterate")
    public String extractDataAndInsert() {
        Map<String, List<String>> extractedData = excelDataReaderService.extractDataFromExcel("src/main/resources/books.xlsx");

        List<String> co_idStrings = extractedData.get("Column 1");
        List<String> phone_id = extractedData.get("Column 2");
        List<String> book_name = extractedData.get("Column 3");
        List<String> author_name = extractedData.get("Column 4");
        List<String> mobileStrings = extractedData.get("Column 9");

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < co_idStrings.size(); i++) {
            String coId = co_idStrings.get(i);
            String phoneId = phone_id.get(i);
            String bookName = book_name.get(i);
            String authorName = author_name.get(i);
            String mobileString = mobileStrings.get(i);

            Timestamp cstDate = new Timestamp(System.currentTimeMillis());

            String sqlQuery = String.format(
                    "INSERT INTO data (co_id, phone_id, book_name, author_name, mobile_string, cst_date) VALUES (%s, %s, '%s', '%s', %s, '%s');",
                    coId, phoneId, bookName, authorName, mobileString, cstDate);
            System.out.println(sqlQuery);
            jdbcTemplate.update(sqlQuery);
        }
        return result.toString();
    }

}

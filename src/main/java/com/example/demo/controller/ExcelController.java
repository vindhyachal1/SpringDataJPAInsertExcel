package com.example.demo.controller;

import com.example.demo.reader.ExcelDataReaderService;
//import com.example.demo.service.ExcelDataExtractorService;
import com.example.demo.service.ExcelDataProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/excel")
public class ExcelController {
    @Autowired
    private ExcelDataReaderService excelDataReaderService;
//    @Autowired
//    private ExcelDataExtractorService excelDataExtractorService;
    @Autowired
    private ExcelDataProcessorService excelDataProcessorService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/iterate")
    public String extractDataAndInsert() {
        Map<String, List<String>> extractedData = excelDataReaderService.extractDataFromExcel("src/main/resources/books.xlsx");

        for (int i = 0; i < extractedData.get("Column 1").size(); i++) {
            Map<String, String> excelData = excelDataProcessorService.extractData(extractedData, i);
            Timestamp cstDate = new Timestamp(System.currentTimeMillis());
            String sqlQuery = String.format(
                    "INSERT INTO data (co_id, phone_id, book_name, author_name, mobile_string, cst_date) VALUES " +
                            "('%s', '%s', '%s', '%s', '%s', '%s')",
                    excelData.get("coId"),
                    excelData.get("phoneId"),
                    excelData.get("bookName"),
                    excelData.get("authorName"),
                    excelData.get("mobileString"),
                    cstDate);
            System.out.println(sqlQuery);
            jdbcTemplate.update(sqlQuery);
        }
        return "Data inserted successfully!";
    }
}
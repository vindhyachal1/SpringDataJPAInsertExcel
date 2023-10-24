package com.example.demo.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

import com.example.demo.reader.ExcelDataReaderService;
import com.example.demo.service.ExcelDataProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/excel")
public class ExcelController {
    @Autowired
    private ExcelDataReaderService excelDataReaderService;
    @Autowired
    private ExcelDataProcessorService excelDataProcessorService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${excel.file.path}")
    private String excelFilePath;

    @GetMapping("/iterate")
    public String extractDataAndInsert() {
        Map<String, List<String>> extractedData = excelDataReaderService.extractDataFromExcel(excelFilePath);
        List<String> coIdList = extractedData.get("Column 1");
        for (String custId : coIdList) {
            Map<String, String> excelData = excelDataProcessorService.extractData(extractedData, coIdList.indexOf(custId));

            String cstDateValue = excelData.get("cst_date");
            Timestamp cstDate;

            if ("currentDate".equalsIgnoreCase(cstDateValue)) {
                cstDate = new Timestamp(System.currentTimeMillis());
            } else {
                // Assuming that cst_date in the Excel is in a specific format (e.g., "dd-MM-yyyy'T'HH:mm:ss'Z'")
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss'Z'");
                try {
                    Date parsedDate = dateFormat.parse(cstDateValue);
                    cstDate = new Timestamp(parsedDate.getTime());
                } catch (ParseException e) {
                    // Handle parsing error, e.g., log the error or set a default value
                    cstDate = new Timestamp(System.currentTimeMillis());
                }
            }

            String sqlQuery = String.format(
                    "INSERT INTO data (co_id, phone_id, book_name, author_name, mobile_string, cst_date) VALUES " +
                            "('%s', '%s', '%s', '%s', '%s', '%s')",
                    excelData.get("coId"),
                    excelData.get("phoneId"),
                    excelData.get("bookName"),
                    excelData.get("authorName"),
                    excelData.get("mobileString"),
                    cstDate.toString()); // Assuming that the date format in your database is acceptable

            System.out.println(sqlQuery);
            jdbcTemplate.update(sqlQuery);
        }
        return "Data inserted successfully!";
    }
}

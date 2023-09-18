package com.example.demo;

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
            data.setCo_id(co_id.get(i));
            data.setPhone(phone_id.get(i));
            data.setBook_name(book_name.get(i));
            data.setAuthor_name(author_name.get(i));
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

        return dto;
    }
}
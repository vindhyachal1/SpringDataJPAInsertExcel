package com.example.demo.controller;

import com.example.demo.dto.ExtractedDataDTO;
import com.example.demo.dto.Student;
import com.example.demo.reader.ExcelDataReaderService;
import com.example.demo.service.ExcelDataService;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    @GetMapping("/extractDataAndInsert")
    public ExtractedDataDTO extractDataAndInsert() {
        Map<String, List<String>> extractedData = excelDataReaderService.extractDataFromExcel("src/main/resources/books.xlsx");

        List<String> co_idStrings = extractedData.get("Column 1");
        List<String> phone_id = extractedData.get("Column 2");
        List<String> book_name = extractedData.get("Column 3");
        List<String> author_name = extractedData.get("Column 4");
        List<String> mobileStrings = extractedData.get("Column 9");

        List<Integer> co_idList = co_idStrings.stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        List<Integer> mobileList = mobileStrings.stream()
                .flatMap(str -> Arrays.stream(str.split(", ")))
                .map(String::trim)
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        List<ExtractedDataDTO> dataList = co_idList.stream().map(co_id -> {
            ExtractedDataDTO data = new ExtractedDataDTO();
            int index = co_idList.indexOf(co_id);

            data.setCo_id(co_id);
            data.setPhone("'" + phone_id.get(index) + "'");
            data.setBook_name("'" + book_name.get(index) + "'");
            data.setAuthor_name("'" + author_name.get(index) + "'");
            data.setMobile(mobileList);
            data.setInsertionTime(new Date());

            return data;
        }).collect(Collectors.toList());
        dataList.forEach(excelDataService::save);

        ExtractedDataDTO dto = new ExtractedDataDTO();
        dto.setCo_idList(co_idList);
        dto.setPhone(String.join(", ", phone_id));
        dto.setBook_name(String.join(", ", book_name));
        dto.setAuthor_name(String.join(", ", author_name));
        dto.setMobile(mobileList);

        System.out.println("CID: " + dto.getCo_id());
        System.out.println("PHONE: " + dto.getPhone());
        System.out.println("BOOK: " + dto.getBook_name());
        System.out.println("AUTHOR: " + dto.getAuthor_name());
        System.out.println("MOBILE: " + dto.getMobile());

        return dto;
    }
}

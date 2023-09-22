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
        ExtractedDataDTO dto = new ExtractedDataDTO();

        List<String> co_idStrings = extractedData.get("Column 1");
        List<String> phone_id = extractedData.get("Column 2");
        List<String> book_name = extractedData.get("Column 3");
        List<String> author_name = extractedData.get("Column 4");
        List<String> mobileStrings = extractedData.get("Column 9");

        // Convert co_idStrings to a list of integers
        List<Integer> co_idList = co_idStrings.stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        // Convert mobileStrings to a list of integers
        List<Integer> mobileList = mobileStrings.stream()
                .flatMap(str -> {
                    String[] numbers = str.split(", ");
                    return Arrays.stream(numbers);
                })
                .map(String::trim)
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        // Insert data into H2 database for ExtractedDataDTO
        for (int i = 0; i < co_idList.size(); i++) {
            ExtractedDataDTO data = new ExtractedDataDTO();
            data.setCo_id(co_idList.get(i));
            data.setPhone("'" + phone_id.get(i) + "'");
            data.setBook_name("'" + book_name.get(i) + "'");
            data.setAuthor_name("'" + author_name.get(i) + "'");
            data.setMobile(mobileList);
            data.setInsertionTime(new Date());

            excelDataService.save(data);
        }

        dto.setCo_idList(co_idList);
        dto.setPhone(String.join(", ", phone_id));
        dto.setBook_name(String.join(", ", book_name));
        dto.setAuthor_name(String.join(", ", author_name));
        dto.setMobile(mobileList); // Set the mobile field as a List<Integer>

        System.out.println("CID: " + dto.getCo_id());
        System.out.println("PHONE: " + dto.getPhone());
        System.out.println("BOOK: " + dto.getBook_name());
        System.out.println("AUTHOR: " + dto.getAuthor_name());
        System.out.println("MOBILE: " + dto.getMobile());

        ///////////////////////////////////
        Student student = new Student();

        List<String> cidStrings = extractedData.get("Column 1");
        List<String> studentName = extractedData.get("Column 5");
        List<String> studentAge = extractedData.get("Column 6");
        List<String> studentGrade = extractedData.get("Column 7");
        List<String> studentAddress = extractedData.get("Column 8");

        List<Integer> idList = cidStrings.stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        for (int i = 0; i < idList.size(); i++) {
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

        System.out.println("NAME: " + dto.getCo_id());
        System.out.println("AGE: " + dto.getPhone());
        System.out.println("GRADE: " + dto.getBook_name());
        System.out.println("ADDRESS: " + dto.getAuthor_name());

        return dto;
    }
}

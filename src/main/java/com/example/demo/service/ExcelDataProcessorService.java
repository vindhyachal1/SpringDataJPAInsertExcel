package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExcelDataProcessorService {

    public Map<String, String> extractData(Map<String, List<String>> extractedData, int index) {
        Map<String, String> excelData = new HashMap<>();

        excelData.put("coId", extractedData.get("Column 1").get(index));
        excelData.put("phoneId", extractedData.get("Column 2").get(index));
        excelData.put("bookName", extractedData.get("Column 3").get(index));
        excelData.put("authorName", extractedData.get("Column 4").get(index));
        excelData.put("mobileString", extractedData.get("Column 9").get(index));

        return excelData;
    }
}



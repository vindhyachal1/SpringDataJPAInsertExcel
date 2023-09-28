package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExcelDataProcessorService {
    private final ExcelDataExtractorService excelDataExtractorService;

    @Autowired
    public ExcelDataProcessorService(ExcelDataExtractorService excelDataExtractorService) {
        this.excelDataExtractorService = excelDataExtractorService;
    }

    public Map<String, String> extractData(Map<String, List<String>> extractedData, int index) {
        Map<String, String> excelData = new HashMap<>();

        excelData.put("coId", excelDataExtractorService.extractColumn1(extractedData).get(index));
        excelData.put("phoneId", excelDataExtractorService.extractColumn2(extractedData).get(index));
        excelData.put("bookName", excelDataExtractorService.extractColumn3(extractedData).get(index));
        excelData.put("authorName", excelDataExtractorService.extractColumn4(extractedData).get(index));
        excelData.put("mobileString", excelDataExtractorService.extractColumn9(extractedData).get(index));

        return excelData;
    }
}



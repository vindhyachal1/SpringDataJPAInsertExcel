package com.example.demo.service;

import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Getter
public class ExcelDataExtractorService {

    public List<String> extractColumn1(Map<String, List<String>> extractedData) {
        return extractedData.get("Column 1");
    }

    public List<String> extractColumn2(Map<String, List<String>> extractedData) {
        return extractedData.get("Column 2");
    }

    public List<String> extractColumn3(Map<String, List<String>> extractedData) {
        return extractedData.get("Column 3");
    }

    public List<String> extractColumn4(Map<String, List<String>> extractedData) {
        return extractedData.get("Column 4");
    }

    public List<String> extractColumn9(Map<String, List<String>> extractedData) {
        return extractedData.get("Column 9");
    }
}


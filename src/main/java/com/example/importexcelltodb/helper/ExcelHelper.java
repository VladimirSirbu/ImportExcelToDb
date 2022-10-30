package com.example.importexcelltodb.helper;

import org.springframework.web.multipart.MultipartFile;

public class ExcelHelper {

    public static String XLSX_FORMAT = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static String XLS_FORMAT = "application/vnd.ms-excel";
    public static String CSV_FORMAT = "text/csv";

    public static boolean hasExcelFormat(MultipartFile file) {
        return XLSX_FORMAT.equals(file.getContentType()) ||
                XLS_FORMAT.equals(file.getContentType()) ||
                CSV_FORMAT.equals(file.getContentType());
    }

}

package com.example.importexcelltodb.service;

import org.springframework.web.multipart.MultipartFile;

public interface ExcelService {

    void saveExcelFileToDataBase(MultipartFile multipartFile);
}

package com.example.importexcelltodb.controller;

import com.example.importexcelltodb.exception.ResponseMessage;
import com.example.importexcelltodb.helper.ExcelHelper;
import com.example.importexcelltodb.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @PostMapping("/upload-and-save-to-db")
    public ResponseEntity<ResponseMessage> saveUploadedFileToDataBase(@RequestBody MultipartFile multipartFile) {
        if (ExcelHelper.hasExcelFormat(multipartFile)) {
            excelService.saveExcelFileToDataBase(multipartFile);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Success"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("Please upload an excel file!"));
    }

}


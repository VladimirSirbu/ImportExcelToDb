package com.example.importexcelltodb.service;

import com.example.importexcelltodb.helper.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    private Environment environment;

    public void saveExcelFileToDataBase(MultipartFile multipartFile) {

        String originalFileName = multipartFile.getOriginalFilename();
        String fileFormat = Objects.requireNonNull(originalFileName).substring(originalFileName.lastIndexOf("."));

        try {
            File file = Files.createTempFile("excel_data", fileFormat).toFile();
            file.deleteOnExit();

            Files.write(file.toPath(), multipartFile.getBytes());

            String[] cmd = {
                    "python",
                    "SaveExcelToDB.py",
                    file.getAbsolutePath(), // Full path to Excel file
                    fileFormat, // file format
                    environment.getProperty("database.url"), // URL to data base
                    StringUtils
                            .replaceSpaceWithUnderscore(Objects.requireNonNull(originalFileName))
                            .substring(0, originalFileName.lastIndexOf("."))
                            .toLowerCase() // Excel file name will be table name from DB
            };

            Process process = Runtime.getRuntime().exec(cmd); // remember that name of tables must be unique
            try {
                process.waitFor(); // may be here is better to specify a time unit. I'm not sure
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            file.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

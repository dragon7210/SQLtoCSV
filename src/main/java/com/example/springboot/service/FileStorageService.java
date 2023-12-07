package com.example.springboot.service;
import com.example.springboot.models.OracleEntity;
import com.example.springboot.repositories.OracleRepository;
import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class FileStorageService {

    private OracleRepository oracleRepository;
    @Autowired
    public FileStorageService(OracleRepository oracleRepository) {
        this.oracleRepository =oracleRepository;
    }
    public void SQLtoCSV(String folderPath){
        List<OracleEntity> oracleEntityList = oracleRepository.findAll();
        if(!oracleEntityList.isEmpty()) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedTime = now.format(formatter);
            Path csvFilePath = Paths.get(folderPath).resolve(formattedTime+".csv");
            try (CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath.toString()))) {
                // Writing the header row
                String[] header = { "Name", "Age" };
                writer.writeNext(header);

                // Writing data rows
                for (OracleEntity item : oracleEntityList) {
                    String[] dataRow = {
                            item.getName(),
                            String.valueOf(item.getAge())
                    };
                    writer.writeNext(dataRow);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}

package com.vydrin.dissertation.services;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;


@Component
public class DataToExcelService {
    public void writeIOstatsToFile(String pathToFile, TreeSet<String> data) throws IOException {
        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet("stats");

        int rowIndex = 0;

        Row headerRow = sheet.createRow(rowIndex++);
        Cell headerInput = headerRow.createCell(0);
        Cell headerOut = headerRow.createCell(1);
        Cell headerValue = headerRow.createCell(2);

        headerInput.setCellValue("inputs");
        headerOut.setCellValue("outs");
        headerValue.setCellValue("count");


        for(String val : data) {
            String value = val.split(":")[1];
            if(Integer.parseInt(value) > 5) {
                String vals[] = val.split(":")[0].split("-");
                Row nextRow = sheet.createRow(rowIndex++);
                nextRow.createCell(0).setCellValue(vals[0]);
                nextRow.createCell(1).setCellValue(vals[1]);
                nextRow.createCell(2).setCellValue(value);
            }
        }


        book.write(new FileOutputStream(pathToFile));
        book.close();
    }
}

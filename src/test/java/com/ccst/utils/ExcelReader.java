package com.ccst.utils;

import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ExcelReader {
    private ExcelReader(){}

    public static List<Map<String,String>> read(String file,String sheetname){
        Path path= Path.of(file);
        if(!Files.exists(path))
        {
            throw new IllegalArgumentException("CSV file not found: "+path.toAbsolutePath());
        }
        try(Workbook workbook= WorkbookFactory.create(path.toFile())){
            Sheet sheet =workbook.getSheet(sheetname);
            if(sheet==null){
                throw new IllegalArgumentException("Sheet" + sheetname + "not found in " + file);
            }
            DataFormatter formatter=new DataFormatter();

            Row header =sheet.getRow(sheet.getFirstRowNum());
            List<String>columns=new ArrayList<>();
            for(Cell cell : header){
                columns.add(formatter.formatCellValue(cell).trim());
            }

            List<Map<String,String>> rows = new ArrayList<>();
            for(int i= sheet.getFirstRowNum() + 1 ; i<=sheet.getLastRowNum();i++){
                Row row = sheet.getRow(i);
                if(row==null){
                    continue;
                }

                Map<String,String>values= new LinkedHashMap<>();
                boolean blank= true;
                for(int c = 0; c < columns.size();c++){
                    String text= formatter.formatCellValue(row.getCell(c));
                    values.put(columns.get(c),text );
                    if(!text.isBlank()){
                        blank=false;
                    }
                }
                if(!blank){
                    rows.add(values);
                }
            }
            return rows;
        } catch (IOException e) {
            throw new IllegalStateException("Could not read " + file, e);
        }
    }
}

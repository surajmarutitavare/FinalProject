//package utils;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//public class ExcelUtility {
//
//
//
//    // Path to the Excel file
//    private static final String FILE_PATH =
//            System.getProperty("user.dir") + "/src/main/resources/test.xlsx";
//
//    // Sheet name inside the Excel file
//    private static final String SHEET_NAME = "Sheet1";
//
//
//
//    private static void createHeaders(Sheet sheet) {
//
//        String[] headers = {
//                "Hospital Name",
//                "Rating",
//                "Parking",
//                "Open24x7 Availability"
//        };
//
//        Row row = sheet.createRow(0);
//
//        for (int i = 0; i < headers.length; i++) {
//            row.createCell(i).setCellValue(headers[i]);
//        }
//    }
//
//
//
//    public static void setCellData(int rowNum, int colNum, String value) {
//
//        try {
//
//            File file = new File(FILE_PATH);
//
//            Workbook workbook;
//            Sheet sheet;
//
//            if (file.exists()) {
//
//                FileInputStream fis = new FileInputStream(file);
//                workbook = new XSSFWorkbook(fis);
//                fis.close();
//
//                sheet = workbook.getSheet(SHEET_NAME);
//
//                if (sheet == null) {
//                    sheet = workbook.createSheet(SHEET_NAME);
//                }
//
//            } else {
//
//                file.getParentFile().mkdirs();
//
//                workbook = new XSSFWorkbook();
//                sheet = workbook.createSheet(SHEET_NAME);
//                createHeaders(sheet);
//            }
//
//            Row row = sheet.getRow(rowNum);
//
//            if (row == null) {
//                row = sheet.createRow(rowNum);
//            }
//
//            Cell cell = row.getCell(colNum);
//
//            if (cell == null) {
//                cell = row.createCell(colNum);
//            }
//
//            cell.setCellValue(value);
//
//            FileOutputStream fos = new FileOutputStream(FILE_PATH);
//            workbook.write(fos);
//
//            fos.close();
//            workbook.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}


package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    private static final String FILE_PATH = System.getProperty("user.dir") + ConfigReader.getProperty("excelPath");
    //            System.getProperty("user.dir") + "/src/main/resources/test.xlsx";

    private static final String SHEET_NAME = "Sheet1";

    private static void createHeaders(Sheet sheet) {

        String[] headers = {
                "Hospital Name",
                "Rating",
                "Parking",
                "Open24x7 Availability"
        };

        Row row = sheet.createRow(0);

        for (int i = 0; i < headers.length; i++) {
            row.createCell(i).setCellValue(headers[i]);
        }
    }

    public static void writeHospitalRow(int rowNum,
                                        String hospitalName,
                                        String rating,
                                        String parking,
                                        String availability) {

        try {
            File file = new File(FILE_PATH);
            Workbook workbook;
            Sheet sheet;
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                workbook = new XSSFWorkbook(fis);
                fis.close();
                sheet = workbook.getSheet(SHEET_NAME);
                if (sheet == null) {
                    sheet = workbook.createSheet(SHEET_NAME);
                }
            } else {
                file.getParentFile().mkdirs();
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet(SHEET_NAME);
                createHeaders(sheet);
            }

            Row row = sheet.getRow(rowNum);

            if (row == null) {
                row = sheet.createRow(rowNum);
            }

            row.createCell(0).setCellValue(hospitalName);
            row.createCell(1).setCellValue(rating);
            row.createCell(2).setCellValue(parking);
            row.createCell(3).setCellValue(availability);

            for (int i = 0; i < 4; i++) {
                sheet.autoSizeColumn(i);
            }

            FileOutputStream fos = new FileOutputStream(FILE_PATH);
            workbook.write(fos);

            fos.close();
            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
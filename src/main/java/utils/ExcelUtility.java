package utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    // Path to the Excel file
    private static final String FILE_PATH =
            System.getProperty("user.dir") + "/src/main/resources/test.xlsx";

    // Sheet name inside the Excel file
    private static final String SHEET_NAME = "Sheet1";

    // Method to read data from a specific cell
    public static String getCellData(int rowNum, int colNum) {
        String data = "";

        // Try-with-resources (auto closes file & workbook)
        try  {
            FileInputStream fis = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(fis);
            // Get the sheet by name
            Sheet sheet = workbook.getSheet(SHEET_NAME);

            // Check if sheet exists
            if (sheet == null) {
                System.out.println("Sheet not found!");
                return "";
            }

            // Get the row from sheet
            Row row = sheet.getRow(rowNum);

            // If row doesn't exist, return empt
            if (row == null) {
                return "";
            }

            // Get the cell from row
            Cell cell = row.getCell(colNum);

            // If cell doesn't exist, return empty
            if (cell == null) {
                return "";
            }

            // Read string value from cell
            data = cell.getStringCellValue();


        } catch (IOException e) {
            // Handle file reading error
            System.out.println("Error reading Excel file: " + e.getMessage());
        }

        // Return the cell data
        return data;
    }
    /*----------------------------------------------------------------------------------------------------------*/

    // Method to write/update data in a specific cell
    public static void setCellData(int rowNum, int colNum, String value) {

        try {
            // Open existing file
            FileInputStream fis = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(fis);

            // Get the sheet
            Sheet sheet = workbook.getSheet(SHEET_NAME);

            // Get the row, create if not exists
            Row row = sheet.getRow(rowNum);
            if (row == null) {
                row = sheet.createRow(rowNum);
            }

            // Get the cell, create if not exists
            Cell cell = row.getCell(colNum);
            if (cell == null) {
                cell = row.createCell(colNum);
            }

            // Set value in the cell
            cell.setCellValue(value);

            fis.close(); // Close input before writing

            //  Write back to Excel
            java.io.FileOutputStream fos = new java.io.FileOutputStream(FILE_PATH);
            workbook.write(fos);

            // Close resources
            fos.close();
            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
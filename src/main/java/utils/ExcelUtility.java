package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// Utility class to handle Excel file operations
public class ExcelUtility {

    // Excel file path from config
    private static final String FILE_PATH = System.getProperty("user.dir") + ConfigReader.getProperty("excelPath");
    //            System.getProperty("user.dir") + "/src/main/resources/test.xlsx";

    // Sheet name in Excel file
    private static final String SHEET_NAME = "Sheet1";

    // Method to create header row in Excel sheet
    private static void createHeaders(Sheet sheet) {

        // Array of column headers
        String[] headers = {
                "Hospital Name",
                "Rating",
                "Parking",
                "Open24x7 Availability"
        };

        // Create first row (row 0) for headers
        Row row = sheet.createRow(0);

        // Loop through headers and add them to cells
        for (int i = 0; i < headers.length; i++) {
            // Create cell and set header text
            row.createCell(i).setCellValue(headers[i]);
        }
    }

    // Method to write hospital data to Excel row
    public static void writeHospitalRow(int rowNum,
                                        String hospitalName,
                                        String rating,
                                        String parking,
                                        String availability) {

        // Error handling
        try {
            // Create file object from path
            File file = new File(FILE_PATH);
            // Variables for workbook and sheet
            Workbook workbook;
            Sheet sheet;
            
            // Check if file already exists
            if (file.exists()) {
                // Open existing Excel file
                FileInputStream fis = new FileInputStream(file);
                // Load workbook from file
                workbook = new XSSFWorkbook(fis);
                // Close file input stream
                fis.close();
                // Get existing sheet by name
                sheet = workbook.getSheet(SHEET_NAME);
                // If sheet doesn't exist, create new sheet
                if (sheet == null) {
                    sheet = workbook.createSheet(SHEET_NAME);
                }
            } 
            // If file doesn't exist
            else {
                // Create parent directories if needed
                file.getParentFile().mkdirs();
                // Create new Excel workbook
                workbook = new XSSFWorkbook();
                // Create new sheet
                sheet = workbook.createSheet(SHEET_NAME);
                // Add headers to new sheet
                createHeaders(sheet);
            }

            // Get row at specified row number
            Row row = sheet.getRow(rowNum);

            // If row doesn't exist, create new row
            if (row == null) {
                row = sheet.createRow(rowNum);
            }

            // Add hospital name to first column
            row.createCell(0).setCellValue(hospitalName);
            // Add rating to second column
            row.createCell(1).setCellValue(rating);
            // Add parking to third column
            row.createCell(2).setCellValue(parking);
            // Add availability to fourth column
            row.createCell(3).setCellValue(availability);

            // Auto-fit all columns to content width
            for (int i = 0; i < 4; i++) {
                // Adjust column width automatically
                sheet.autoSizeColumn(i);
            }

            // Create output stream to write Excel file
            FileOutputStream fos = new FileOutputStream(FILE_PATH);
            // Write workbook to file
            workbook.write(fos);

            // Close output stream
            fos.close();
            // Close workbook
            workbook.close();

        } 
        // Handle any errors
        catch (Exception e) {
            // Print error details
            e.printStackTrace();
        }
    }
}
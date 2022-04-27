package ExcelOperations;


import java.io.FileInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class BaseExcel {

       public void writeExcel(String filepath, int no, int i, int i2, String string)  throws IOException {
             
              //String filepath = "data/TestDataSheet.xlsx";
             
              FileInputStream fis = new FileInputStream(filepath);
             
              Workbook workbook = new XSSFWorkbook(fis);
             
              Sheet sheet = workbook.getSheetAt(no);
             
              int lastRow = sheet.getLastRowNum();
             
                            
              Row row = sheet.getRow(i2);
             
              Cell cell = row.createCell(i);
             
              cell.setCellValue(string);
             
             
              FileOutputStream fos = new FileOutputStream(filepath);
             
              workbook.write(fos);
             
              fos.close();
             
              
              }
             
             

       public String[][] readExcel(String filepath, int no) {
             // TODO Auto-generated method stub
             

                    // A Two dimensional array of Strings which represents the data in the
                    // sheet
                    String[][] data = null;
                    try {
                           // A Buffered File Input Stream to read the data
                           InputStream is = new BufferedInputStream(new FileInputStream(filepath));
                           // Workbook representing the excel file
                           XSSFWorkbook wb = new XSSFWorkbook(is);
                           // Next a sheet which represents the sheet within that excel file
                           XSSFSheet sheet = wb.getSheetAt(no);
                           // No of rows in the sheet
                           int rowNum = sheet.getLastRowNum() + 1;
                           // No of columns in the sheet
                           int colNum = sheet.getRow(0).getLastCellNum();
                           data = new String[rowNum][colNum];
                           for (int i = 0; i < rowNum; i++) {
                                 // Get the row
                                 XSSFRow row = sheet.getRow(i);
                                 for (int j = 0; j < colNum; j++) {
                                        // Get the columns or cells for the first row and keep
                                        // looping
                                        // for the other rows
                                        XSSFCell cell = row.getCell(j);
                                        //XSSFCell cell = row.getCell(j);
                    					if(cell==null){
                    						cell = row.getCell(j, Row.CREATE_NULL_AS_BLANK);	
                    					} 
					/*
					 * if(cell==null) { row.getCell(cell,Row.CREATE_NULL_AS_BLANK); }
					 */
                                        // Make a call to the method cellToString which actually
                                        // converts the cell contents to String
                                        String value1 = cellToString(cell);
                                        String value=value1.replace(".0","");
                                        data[i][j] = value;
                                        // Logic for handling the data
                                        // You can write the logic here, or leave the method as it
                                        // is to return a two dimensional array
                                        // representing the excel data
                                        System.out.println("Value:" + value);
                                 }

                           }
                    } catch (Exception e) {
                           e.printStackTrace();
                    }
                    return data;

             }

             private static String cellToString(XSSFCell cell) {

                    Object result;

                    switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_NUMERIC:
                           result = cell.getNumericCellValue();
                           break;

                    case Cell.CELL_TYPE_STRING:
                           result = cell.getStringCellValue();
                           break;

                    case Cell.CELL_TYPE_BOOLEAN:
                           result = cell.getBooleanCellValue();
                           break;

                    case Cell.CELL_TYPE_FORMULA:
                           result = cell.getCellFormula();
                           break;
                           
                    case Cell.CELL_TYPE_BLANK:
                        result = cell.getNumericCellValue();
                        break;

                    default:
                           throw new RuntimeException("Unknown Cell Type");
                    }

                    return result.toString();
             }

       }

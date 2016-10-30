package utility;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by Administrator on 9/11/2016.
 */

public class ExcelDataConfig {
    XSSFWorkbook wb;
    XSSFSheet sheet1;
    File excelFile;
    private int rowsCount;
    private int columnCount;

    public ExcelDataConfig(String excelPath){
        try {
            excelFile = new File(excelPath);
            FileInputStream fis = new FileInputStream(excelFile);
            wb = new XSSFWorkbook(fis);
            sheet1 = wb.getSheetAt(0);
            rowsCount = getRowCount();
            columnCount = getColumnCount();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public String getData(int row, int column){
        return sheet1.getRow(row).getCell(column).getStringCellValue();
    }

    public Object[][] getAllData(){
        Object[][] data = new Object[rowsCount][columnCount];
        for (int i = 0; i < rowsCount; i++){
            for (int j = 0; j<columnCount; j++ ) {
                data[i][j] = getData(i, j);
                System.out.println(data[i][j]);
            }
        }
        return data;
    }


    public void setData(int row, int column, String data){
        FileOutputStream fos;
        sheet1.getRow(row).createCell(column).setCellValue(data);
        try {
            fos = new FileOutputStream(excelFile);
            wb.write(fos);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

     private int getRowCount() {
        return sheet1.getLastRowNum() + 1;
    }


    private int getColumnCount() {
        return sheet1.getRow(0).getLastCellNum();
    }

    public void quit(){
        try {
            wb.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}

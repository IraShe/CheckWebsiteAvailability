package utility;

import org.testng.annotations.DataProvider;

/**
 * Created by Administrator on 9/19/2016.
 */
public class ExcelTestData extends TestBase {
    protected static ExcelDataConfig eFile;

    @DataProvider(name = "excelData")
    public  Object[][] passData(){
        eFile = new ExcelDataConfig("./data/DataFile.xlsx");
        return eFile.getAllData();
    }
}

package KeyWordEngine;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static Base.OpenBrowser.openBrowser;

public class KeyWordEngine {

    WebDriver driver;
    public void startEngine(String filePath,String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getPhysicalNumberOfRows();

        for (int i=0;i<rowCount-1;i++)
        {
            XSSFRow row = sheet.getRow(i+1);

            String locatorColValue = row.getCell(1).toString().trim();
            String locatorName="";
            String locatorValue = "";
            if (!locatorColValue.equalsIgnoreCase("NA"))
            {
                // "id=login-username" --> locatorColValue

                locatorName = locatorColValue.split("=")[0]; // id
                locatorValue = locatorColValue.split("=")[1]; // login-username
            }

            String actions = row.getCell(2).toString().trim();
            String value = row.getCell(3).toString().trim();

            switch (actions)
            {
                case "open browser" : driver = openBrowser(value);
                                        break;
                case "enter url" : driver.get(value);
                                    break;

                case "assertWithUrl" :
                    Assert.assertEquals(driver.getCurrentUrl(),value,"incorrect page");
                    break;
                    
                case "assertWithPageTitle" :
                    Assert.assertEquals(driver.getTitle(),value,"incorrect page");
                    break;
            }
            
            switch (locatorName)
            {
                case "id" : if(actions.equalsIgnoreCase("click"))
                                driver.findElement(By.id(locatorValue)).click();
                            else if (actions.equalsIgnoreCase("sendkeys"))
                                driver.findElement(By.id(locatorValue)).sendKeys(value);
                            break;
                case "name" : if(actions.equalsIgnoreCase("click"))
                                driver.findElement(By.name(locatorValue)).click();
                            else if (actions.equalsIgnoreCase("sendkeys"))
                                driver.findElement(By.name(locatorValue)).sendKeys(value);
                                 break;
                case "linkText" :
                    driver.findElement(By.linkText(locatorValue)).click();
                    break;
            }

        }


    }
}

package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class OpenBrowser {

    public static WebDriver driver;

    public static  WebDriver openBrowser(String browserName)
    {
        if (browserName.equalsIgnoreCase("chrome"))
            driver = new ChromeDriver();
        else  if (browserName.equalsIgnoreCase("fireFox"))
            driver = new FirefoxDriver();

        driver.manage().window().maximize();

        return driver;

    }
}

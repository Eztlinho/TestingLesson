
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Steve on 1/26/2017.
 */
public class MyWebdriver {

    private static MyWebdriver INSTANCE = new MyWebdriver();

    public static MyWebdriver getInstance() {return INSTANCE;}

    protected WebDriver driver;

    protected void initWebdriver(String url) {
        driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        navigateTo(url);
    }

    protected void navigateTo(String url) {
        driver.navigate().to(url);
    }

    protected void closeConnection() {
        driver.close();
    }

}

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Steve on 12/8/2017.
 */
public class WikipediaWebdriverTest {

    public static WebDriver WEB_DRIVER = null;

    @Test
    public void reasonsAreAllDisplayed() throws InterruptedException {
        MyWebdriver.getInstance().initWebdriver("https://en.wikipedia.org");
        WEB_DRIVER = MyWebdriver.getInstance().driver;

        WEB_DRIVER.findElement(By.xpath("//input[@type='search']")).sendKeys("Java\n");
        String text = WEB_DRIVER.findElement(By.xpath("//h1")).getText();
        Assert.assertEquals(text, "Java");

        WEB_DRIVER.close();
    }
}

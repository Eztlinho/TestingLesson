import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Steve on 12/8/2017.
 */
public class WebdriverTest {

    public static WebDriver WEB_DRIVER = null;

    @Test
    public void testNameIsCorrecltySend() throws InterruptedException {
        MyWebdriver.getInstance().initWebdriver("file:///C:/Users/Steve/Projets/JavaTests/webapp/index.html");
        WEB_DRIVER = MyWebdriver.getInstance().driver;
        Assert.assertEquals(WEB_DRIVER.findElement(By.xpath("//h1")).getText().trim(), "Roman Empire vs Zombie Rampage");
        WEB_DRIVER.findElement(By.xpath("//input[@type='text']")).sendKeys("LeGuerrier92");
        WEB_DRIVER.findElement(By.xpath("//input[@type='submit']")).click();

        Assert.assertEquals(WEB_DRIVER.findElement(By.xpath("//h1")).getText(), "Roman Empire vs Zombie Rampage");
        Thread.sleep(1000);
        Assert.assertEquals(WEB_DRIVER.findElement(By.xpath("//h3[@id='name']")).getText(), "LeGuerrier92");

        MyWebdriver.getInstance().closeConnection();
    }
}

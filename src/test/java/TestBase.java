import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;

public class TestBase {
    private WebDriver driver;
    By forgotPassword = By.linkText("Forgot Password");
    By retrievePassword = By.id("form_submit");
    By Email = By.id("email");
    @BeforeTest
    public void Setup()
    {
        driver = new EdgeDriver();
        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().window().maximize();
    }
    @Test
    public void SendKeysandClick() throws InterruptedException {
        driver.findElement(forgotPassword).click();
        Thread.sleep(3000);
        driver.findElement(Email).sendKeys("ahmedashraf09@gmail.com");
        Thread.sleep(3000);
        driver.findElement(retrievePassword).click();
    }

    @AfterTest
    public void Close()
    {
      // driver.quit();
    }

}

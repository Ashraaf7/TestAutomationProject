import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

public class TestBase {
    private WebDriver driver;
    By forgotPassword = By.linkText("Forgot Password");
    By retrievePassword = By.id("form_submit");
    By Email = By.id("email");
    By DropDownButton = By.linkText("Dropdown");
    By DropDownList = By.xpath("//select[@id='dropdown']");
    @BeforeMethod
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
        driver.navigate().back();
        driver.navigate().back();
    }

    @Test
    public void DropDown() throws InterruptedException {
        driver.findElement(DropDownButton).click();
        Thread.sleep(3000);
        Select select = new Select(driver.findElement(DropDownList));
        Thread.sleep(3000);
        select.selectByVisibleText("Option 1");
        Thread.sleep(3000);
    }

    @AfterMethod
    public void Close()
    {
      // driver.quit();
    }

}

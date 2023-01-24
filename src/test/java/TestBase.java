import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

import java.util.Set;

public class TestBase {
    private WebDriver driver;
    By forgotPassword = By.linkText("Forgot Password");
    By retrievePassword = By.id("form_submit");
    By Email = By.id("email");
    By DropDownButton = By.linkText("Dropdown");
    By DropDownList = By.xpath("//select[@id='dropdown']");
    By ChechListButton = By.linkText("Checkboxes");
    By CheckBox2 = By.xpath("(//input[@type='checkbox'])[2]");
    By ContextMenu = By.linkText("Context Menu");
    By Rectangle = By.id("hot-spot");
    By DragDrop = By.linkText("Drag and Drop");
    By Source = By.id("column-a");
    By destination = By.id("column-b");
    By WindowHandleButton = By.linkText("Multiple Windows");
    By ClickHere = By.cssSelector("#content [target='_blank']");
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
    @Test
    public  void CheckList()
    {
        driver.findElement(ChechListButton).click();
        driver.findElement(CheckBox2).click();
        System.out.println( driver.findElement(CheckBox2).isSelected());
    }

    @Test
    public void RightClick ()
    {
        driver.findElement(ContextMenu).click();
        Actions actions = new Actions(driver);
        actions.contextClick(driver.findElement(Rectangle)).perform();;
    }

    @Test
    public void DragDrop() throws InterruptedException {
        driver.findElement(DragDrop).click();
        Actions actions = new Actions(driver);
        actions.dragAndDrop(driver.findElement(Source),driver.findElement(destination)).perform();
        Thread.sleep(2000);
    }
    @Test
    public void ClickHold() throws InterruptedException {
        driver.findElement(DragDrop).click();
        Actions actions = new Actions(driver);
        actions.clickAndHold(driver.findElement(destination))
                .moveToElement(driver.findElement(Source))
                .release().build().perform();
       // Thread.sleep(2000);
    }

    @Test
    public void MultipleWindows() throws InterruptedException {
        driver.findElement(WindowHandleButton).click();
        String CurrentPage =driver.getWindowHandle();
        Thread.sleep(2000);
        driver.findElement(ClickHere).click();
        Thread.sleep(2000);
        System.out.println(CurrentPage);
        driver.switchTo().window(CurrentPage);
        Set<String> Handles = driver.getWindowHandles();
        for (String s : Handles)
        {
            if(s != CurrentPage)
            driver.switchTo().window(s);
        }


    }


    @AfterMethod
    public void Close()
    {
      // driver.quit();
    }

}

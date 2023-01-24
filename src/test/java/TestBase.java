import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
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
    By Disappear = By.linkText("Disappearing Elements");
    By Gallery = By.linkText("Gallery");
    By NotFound = By.xpath("//body /h1");
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
        SoftAssert softAssert = new SoftAssert();
        driver.findElement(ChechListButton).click();
        driver.findElement(CheckBox2).click();
       boolean actual =  driver.findElement(CheckBox2).isSelected();
     //Hard Assertion
       Assert.assertEquals(actual,true);
       //Soft Assertion
        softAssert.assertEquals(actual,false);
       softAssert.assertAll();

    }

    @Test
    public void RightClick ()
    {
        WebElement RectangleElement= driver.findElement(Rectangle);
        driver.findElement(ContextMenu).click();
        Actions actions = new Actions(driver);
        actions.contextClick(RectangleElement).perform();;
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
    @Test
    public void Wait ()
    {
        driver.findElement(Disappear).click();
        driver.navigate().refresh();
        //implicitlyWait
       // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //Explicity Wait
        new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(Gallery));
        driver.findElement(Gallery).click();
        String Not = driver.findElement(NotFound).getText();
        Assert.assertEquals( Not ,"Not Found");
    }

    @AfterMethod
    public void Close()
    {
       driver.quit();
    }

}

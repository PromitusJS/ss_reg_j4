package stepDefinition;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.net.URL;

import static org.junit.Assert.assertEquals;

public class Test_Steps {
    private WebDriver driver;
    private String baseUrl;
    private String nodeURL;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();


    @Before
    public void setUp() throws Exception {
        baseUrl = "http://www.selectspecs.com/";
        nodeURL = "http://46.4.106.8:4444/wd/hub";

        DesiredCapabilities caps = DesiredCapabilities.chrome();
        caps.setBrowserName("chrome");
        caps.setPlatform(Platform.LINUX);
        driver = new RemoteWebDriver(new URL(nodeURL),caps);
    }


    @Given("^User is on Main Page$")
    public void user_is_on_Main_Page() throws Throwable {
        driver.get("https://www.selectspecs.com/auth/logout/");
    }

    @When("^User click on Join/Sign in button$")
    public void user_click_on_Join_Sign_in_button() throws Throwable {
        driver.findElement(By.linkText("Join / Sign in")).click();
    }

    @Then("^User fill all required fields and click continue$")
    public void user_fill_all_required_fields_and_click_continue() throws Throwable {
        driver.findElement(By.id("email")).clear();
        Long random = Math.round(Math.random()*1357);
        driver.findElement(By.id("email")).sendKeys("selenium" + random + "@mailinator.com");
        System.out.println("selenium" + random + "@mailinator.com");
        driver.findElement(By.id("accountNo")).click();
        driver.findElement(By.id("submit_btn")).click();
        driver.findElement(By.id("rFName")).clear();
        driver.findElement(By.id("rFName")).sendKeys("SelectSpecs");
        driver.findElement(By.id("rLName")).clear();
        driver.findElement(By.id("rLName")).sendKeys("TestUser");
        new Select(driver.findElement(By.id("rBCountry"))).selectByVisibleText("United Kingdom");
        driver.findElement(By.id("rPhone")).clear();
        driver.findElement(By.id("rPhone")).sendKeys("0971234567");
        driver.findElement(By.id("rPassword")).clear();
        String password = "testqa123";
        driver.findElement(By.id("rPassword")).sendKeys(password);
        System.out.println(password);
        driver.findElement(By.id("rBZip")).clear();
        driver.findElement(By.id("rBZip")).sendKeys("07400");
        driver.findElement(By.id("rBAddress1")).clear();
        driver.findElement(By.id("rBAddress1")).sendKeys("Ukraine");
        driver.findElement(By.id("rBCity")).clear();
        driver.findElement(By.id("rBCity")).sendKeys("Kyiv");
        driver.findElement(By.id("rBState")).clear();
        driver.findElement(By.id("rBState")).sendKeys("Kyiv region");
        driver.findElement(By.id("rBAddress2")).clear();
        driver.findElement(By.id("rBAddress2")).sendKeys("Almazova");
        driver.findElement(By.id("yesBill")).click();
        driver.findElement(By.name("submit")).click();
        assertEquals("Thank you for registering", driver.findElement(By.cssSelector("#message > p")).getText());
        assertEquals("Prescription Glasses & Sunglasses Online | SelectSpecs", driver.getTitle());
        System.out.println("Test passed");
    }
    @After
    public void tearDown() throws Exception {
        driver.quit();

    }
}

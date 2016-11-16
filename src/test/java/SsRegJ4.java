import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SsRegJ4 {
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

    @Test
    public void testSsRegJ4() throws Exception {
        driver.get("https://www.selectspecs.com/auth/logout/");
        driver.findElement(By.linkText("Join / Sign in")).click();
        driver.findElement(By.id("email")).clear();
        Long random = Math.round(Math.random()*1357);
        driver.findElement(By.id("email")).sendKeys("selenium" + random + "@mailinator.com");
        System.out.println("selenium" + random + "@mailinator.com");
        driver.findElement(By.id("accountNo")).click();
        driver.findElement(By.id("submit_btn")).click();
        driver.findElement(By.id("rFName")).clear();
        driver.findElement(By.id("rFName")).sendKeys("Taras");
        driver.findElement(By.id("rLName")).clear();
        driver.findElement(By.id("rLName")).sendKeys("Sukhenko");
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
        driver.findElement(By.id("rBCity")).sendKeys("Brovary");
        driver.findElement(By.id("rBState")).clear();
        driver.findElement(By.id("rBState")).sendKeys("Kyiv region");
        driver.findElement(By.id("rBAddress2")).clear();
        driver.findElement(By.id("rBAddress2")).sendKeys("Gagarina");
        driver.findElement(By.id("yesBill")).click();
        driver.findElement(By.name("submit")).click();
        assertEquals("Thank you for registering", driver.findElement(By.cssSelector("#message > p")).getText());
        assertEquals("Prescription Glasses & Sunglasses Online | SelectSpecs", driver.getTitle());
        System.out.println("Test passed");
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

import static org.junit.Assert.fail;

public class logoSearch {
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
        driver.get("https://www.selectspecs.com");
        String pageTitle = driver.getTitle();
        System.out.println(pageTitle);
        if(pageTitle.equals("Prescription Glasses & Sunglasses Online | SelectSpecs") ) {
            System.out.println("Test passed");
        }
        else {
            System.out.println("Test failed");

        }
      //  Assert.assertEquals("Prescription Glasses & Sunglasses Online | SelectSpecs",driver.getTitle());
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

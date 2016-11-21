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

public class ContactLenses {
    private WebDriver driver;
    private String baseUrl;
    private String nodeURL = "http://46.4.106.8:4444/wd/hub";
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        {
            baseUrl = "http://www.selectspecs.com/";
            nodeURL = "http://46.4.106.8:4444/wd/hub";

            DesiredCapabilities caps = DesiredCapabilities.chrome();
            caps.setBrowserName("chrome");
            caps.setPlatform(Platform.LINUX);
            driver = new RemoteWebDriver(new URL(nodeURL),caps);
        }
    }

    @Test
    public void testContactLenses() throws Exception {
        //Заходим на главную
        driver.get("https://www.selectspecs.com/auth/logout/");
//Проверяем тайтл главной страницы
   assertEquals("Prescription Glasses & Sunglasses Online | SelectSpecs", driver.getTitle());
        driver.findElement(By.cssSelector("#site-flag > div")).click();
        new Select(driver.findElement(By.id("curr"))).selectByVisibleText("£ GBP");
//Проверяем наличие кнопки "Логин"
     assertEquals("Join / Sign in", driver.findElement(By.linkText("Join / Sign in")).getText());
//Нажимаем на кнопку логин
        driver.findElement(By.linkText("Join / Sign in")).click();
//Проверяем страницу на которой находимся - логин?
    assertEquals("Login | SelectSpecs.com", driver.getTitle());
//Заполняем поле почта
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("selenium607@mailinator.com");
//Подтверждаем, что у нас есть пароль
        driver.findElement(By.id("accountYes")).click();
//Вводим пароль
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("testqa123");
//Проверяем наличие кнопки "войти"
        assertEquals("CONTINUE SECURELY »", driver.findElement(By.id("submit_btn")).getText());
//Нажимаем кнопку войти
        driver.findElement(By.id("submit_btn")).click();
//Переходим на закладку с контактными линзами
        driver.get("http://www.selectspecs.com/contact-lenses/");
//Проверяем страницу на которой находимся
        assertEquals("Contact lenses online. Fast delivery | SelectSpecs", driver.getTitle());
//Проверяем наличие видов линз:
        assertEquals("Monthlies »", driver.findElement(By.linkText("Monthlies »")).getText());
        assertEquals("Weeklies »", driver.findElement(By.linkText("Weeklies »")).getText());
        assertEquals("Dailies »", driver.findElement(By.linkText("Dailies »")).getText());
//Выбираем "ежедневные" линзы
        driver.findElement(By.linkText("Dailies »")).click();
//Прроверяем название линз
      // assertEquals("YOUR EYES DAILY", driver.findElement(By.cssSelector("div.productModel.ellipsis")).getText());
//Кликаем по ним
        driver.findElement(By.cssSelector("div.productModel.ellipsis")).click();
//Проверяем тайтл страницы с выбраныыми линзами
        assertEquals("Your Eyes Daily contact lenses by Bausch & Lomb | SelectSpecs", driver.getTitle());
//Запоминаем в переменную название линз
        String expectedName = driver.findElement(By.cssSelector("h1.productTitle")).getText();
//Выводим название название которое запомнили
        System.out.println("Expected item name  " + expectedName);
//Запоминаем цену выбранных линз
        String expectedPrice = driver.findElement(By.cssSelector("span.productPagePrice")).getText();
//Выводим в консоль цену выбранных линз
        System.out.println("Expected price is " + expectedPrice);


//Проверяем наличие кнопки "добавить в корзину"
        assertEquals("ADD TO BASKET »", driver.findElement(By.cssSelector("button.button.largeFwCTA")).getText());
//Нажимаем кнопку "добавить в корзину"
        driver.findElement(By.cssSelector("button.button.largeFwCTA")).click();
//Запоминаем текущее название линз
        String currentName = driver.findElement(By.linkText("Bausch & Lomb Your Eyes Daily")).getText();
//Выводим текущее название линз
        System.out.println("Current name  is " + currentName);
//Проверяем наличие кнопки "Перейти в чекаут"
      // assertEquals("CONTINUE TO SECURE CHECKOUT »", driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div[2]/a")).getText());
//Нажимаем кнопку чекаут
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div[2]/a")).click();

       assertEquals("Order Details & Payment | SelectSpecs.com", driver.getTitle());

        String currentPrice = driver.findElement(By.xpath("//*[@id=\"payment\"]/div[3]/div/div[2]/div[1]/div/div/div[2]/p[3]/span/strong")).getText();
        System.out.println("Current price is " + currentPrice);
        if(currentName.equals(expectedName)){
            System.out.println("Names are the same - OK");
        } else {
            System.out.println("Names aren't the same - FAIL");
        }
        if (expectedPrice.equals(currentPrice)){
            System.out.println("Prices are the same  - OK");
        }else {
            System.out.println("Prices aren't the same - FAIL");
        }


        assertEquals("", driver.findElement(By.xpath("(//*[@id=\"payment\"]/div[5]/div[2]/img[1])")).getText());
        assertEquals("", driver.findElement(By.xpath("(//*[@id=\"payment\"]/div[5]/div[2]/img[2])")).getText());
        assertEquals("", driver.findElement(By.xpath("(//*[@id=\"payment\"]/div[5]/div[2]/img[3])")).getText());
        assertEquals("", driver.findElement(By.xpath("(//*[@id=\"payment\"]/div[5]/div[2]/img[4])")).getText());
        assertEquals("", driver.findElement(By.xpath("(//*[@id=\"payment\"]/div[5]/div[2]/img[5])")).getText());
        driver.findElement(By.xpath("(//*[@id=\"payment\"]/div[5]/div[2]/img[1])")).click();

       // assertEquals("CONFIRM AND CONTINUE TO SECURE PAYMENT »", driver.findElement(By.cssSelector("button.button.largeFwCTA")).getText());
      //  driver.findElement(By.cssSelector("button.button.largeFwCTA")).click();
        assertEquals("Payment reference:", driver.findElement(By.className("field-label")).getText());
       // String orderNumber = driver.findElement(By.xpath("//div[2]/div/p[3]/strong")).getText();
        // ERROR: Caught exception [ERROR: Unsupported command [getEval | ${orderNumber} >=0 ? true : false | ]]
        driver.get("https://www.selectspecs.com/auth/logout/");
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

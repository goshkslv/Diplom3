package org.example.webDriverSettings;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pageObject.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class WebDriverSettings {

    public static WebDriver setBrowser() {
        WebDriverManager.chromedriver().setup();
        if (System.getProperty("browser.type") != null && System.getProperty("browser.type").equals("yandex")) {
            System.setProperty("webdriver.chrome.driver", Constants.YANDEX_BROWSER_TYPE);
        }
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        WebDriverManager.chromedriver().setup();
        WebDriver driver;
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }
}

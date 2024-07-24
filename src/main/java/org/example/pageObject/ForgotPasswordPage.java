package org.example.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriver driver;
    private final By signInButton = By.xpath(".//a[text()='Войти']");
    public void signInButtonPush() {
        driver.findElement(signInButton).click();
    }
}

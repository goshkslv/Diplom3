package org.example.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserProfilePage {
    private final WebDriver driver;
    private final By profileExitButton = By.xpath(".//button[text()='Выход']");
    private final By userProfileButton = By.xpath(".//a[text()='Профиль']");
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    private final By stellarLogoButton = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");

    public UserProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public void pushProfileExitButton(){
        driver.findElement(profileExitButton).click();
    }

    public void pushProfileButton(){
        driver.findElement(userProfileButton).click();
    }

    public void pushConstructorButton(){
        driver.findElement(constructorButton).click();
    }

    public void pushLogoButton(){
        driver.findElement(stellarLogoButton).click();
    }

    public void waitingUserProfileLoad() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(userProfileButton));
    }


}

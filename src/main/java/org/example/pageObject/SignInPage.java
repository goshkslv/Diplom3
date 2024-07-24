package org.example.pageObject;

import org.example.UserInfo.UserInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {
    private final WebDriver driver;
    private final By userEmailInput = By.xpath(".//label[text()='Email']/..//input");
    private final By userPasswordInput = By.xpath(".//label[text()='Пароль']/..//input");
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    private final By loginTitle = By.xpath(".//h2[text()='Вход']");

    public SignInPage(WebDriver driver) {
        this.driver = driver;
    }

    public void insertEmail(String email){
        driver.findElement(userEmailInput).sendKeys(email);
    }

    public void insertPassword(String password){
        driver.findElement(userPasswordInput).sendKeys(password);
    }

    public void waitingLoginPage(){
        new WebDriverWait(driver, 8).until(ExpectedConditions.visibilityOfElementLocated(loginTitle));
    }

    public void loginButtonClick(){
        driver.findElement(loginButton).click();
    }

    public void insertCredintalsAndButtonClick(UserInfo userInfo){
        insertEmail(userInfo.getEmail());
        insertPassword(userInfo.getPassword());
        driver.findElement(loginButton).click();
    }
}

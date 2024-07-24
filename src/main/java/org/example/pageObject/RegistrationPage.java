package org.example.pageObject;

import org.example.UserInfo.UserInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class RegistrationPage {
    WebDriver driver;

    private final By userNameInput = By.xpath(".//label[text()='Имя']/..//input");
    private final By userEmailInput = By.xpath(".//label[text()='Email']/..//input");
    private final By userPasswordInput = By.xpath(".//label[text()='Пароль']/..//input");
    private final By registrationButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By wrongPasswordMessage = By.xpath(".//p[text()='Некорректный пароль']");
    private final By loginButton = By.xpath(".//a[text()='Войти']");

    public RegistrationPage(WebDriver driver){
        this.driver = driver;
    }

    public void insertUserName(String name){
        driver.findElement(userNameInput).sendKeys(name);
    }

    public void insertUserEmail(String email){
        driver.findElement(userEmailInput).sendKeys(email);
    }

    public void insertUserPassword(String password){
        driver.findElement(userPasswordInput).sendKeys(password);
    }

    public void clickRegisterButton(){
        driver.findElement(registrationButton).click();
    }

    public boolean checkWrongPasswordMessage(){
        return driver.findElement(wrongPasswordMessage).isDisplayed();
    }
    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }
    public void waitingForSignUpPageLoading() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(registrationButton));
    }

    public void insertUserDataAndClick(UserInfo userInfo){
        insertUserName(userInfo.getName());
        insertUserEmail(userInfo.getEmail());
        insertUserPassword(userInfo.getPassword());
        clickRegisterButton();
    }

}

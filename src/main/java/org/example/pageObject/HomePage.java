package org.example.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private final WebDriver driver;
    // Кнопка "Войти в аккаунт"
    private final By mainSignInButton = By.xpath(".//button[text()='Войти в аккаунт']");
    //кнопка «Личный кабинет»
    private final By myAccount = By.xpath(".//p[text()='Личный Кабинет']");
    //раздел "Булки"
    private final By breadSection = By.xpath(".//span[text()='Булки']/..");
    //раздел "Соусы"
    private final By sauceSection = By.xpath(".//span[text()='Соусы']/..");
    //раздел "Начинки"
    private final By fillingSection = By.xpath(".//span[text()='Начинки']/..");
    private final By orderPlaceButton = By.xpath(".//button[text()='Оформить заказ']");
    private final By createBurgerText = By.xpath(".//*[text()='Соберите бургер']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void pushMainSighInButton() {
        driver.findElement(mainSignInButton).click();
    }

    public void pushMyAccountButton() {
        driver.findElement(myAccount).click();
    }

    public void clickBreadSection() {
        driver.findElement(breadSection).click();
    }

    public void clickSauceSection() {
        driver.findElement(sauceSection).click();
    }

    public void clickFillingSection() {
        driver.findElement(fillingSection).click();
    }

    public boolean selectBreadSectionButton() {
        return driver.findElement(breadSection).getAttribute("class").contains("current");
    }

    public boolean selectSauceSectionButton() {
        return driver.findElement(sauceSection).getAttribute("class").contains("current");
    }

    public boolean selectFillingSectionButton() {
        return driver.findElement(fillingSection).getAttribute("class").contains("current");
    }

    public void waitingForMainPageLoading() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(createBurgerText));
    }

    public boolean orderButtonIsDisplayed(){
        return driver.findElement(orderPlaceButton).isDisplayed();
    }
}

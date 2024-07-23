import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.example.UserInfo.UserInfo;
import org.example.UserInfo.UserRandomizer;
import org.example.UserInfo.UserSteps;
import org.example.pageObject.*;
import org.example.webDriverSettings.WebDriverSettings;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class LoginUserTest {

    public static UserInfo userInfo;
    public static SignInPage signInPage;
    public HomePage homePage;
    public static String accessToken;
    public static WebDriver driver;

    @Before
    public void startUp() {

        driver = WebDriverSettings.setBrowser();
        homePage = new HomePage(driver);
        signInPage = new SignInPage(driver);
        RestAssured.baseURI = Constants.BURGER_MAIN_PAGE;
        userInfo = UserRandomizer.userWithRandomData();
        accessToken = UserSteps.createNewUser(userInfo).path("accessToken");
    }

    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
        UserSteps.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Авторизация по кнопке Войти в аккаунт")
    public void testLoginFromHomePageLoginButton() {
        driver.get(Constants.BURGER_MAIN_PAGE);
        homePage.waitingForMainPageLoading();
        homePage.pushMainSighInButton();
        signInPage.insertCredintalsAndButtonClick(userInfo);
        assertTrue("Вход выполнен", homePageSuccessLogin());
    }

    @Test
    @DisplayName("Авторизация по кнопке Личный кабинет")
    public void testLoginFromHomePageMyAccountButton() {
        driver.get(Constants.BURGER_MAIN_PAGE);
        homePage.waitingForMainPageLoading();
        homePage.pushMyAccountButton();
        signInPage.insertCredintalsAndButtonClick(userInfo);
        assertTrue("Вход выполнен", homePageSuccessLogin());
    }

    @Test
    @DisplayName("Проверка авторизации на странице регистрация")
    public void testLoginFromRegisterPage() {
        driver.get(Constants.BURGER_MAIN_PAGE + Constants.REGISTER_PAGE);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.clickLoginButton();
        signInPage.insertCredintalsAndButtonClick(userInfo);
        assertTrue("Вход выполнен", homePageSuccessLogin());
    }

    @Test
    @DisplayName("Проверка авторизации со страницы восстановления пароля")
    public void testLoginFromForgotPasswordPage() {
        driver.get(Constants.BURGER_MAIN_PAGE + Constants.FORGOT_PASSWORD);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.signInButtonPush();
        signInPage.insertCredintalsAndButtonClick(userInfo);
        assertTrue("Вход выполнен", homePageSuccessLogin());
    }

    @Step
    public static boolean homePageSuccessLogin() {
        HomePage homePage = new HomePage(driver);
        homePage.waitingForMainPageLoading();
        return homePage.orderButtonIsDisplayed();
    }
}

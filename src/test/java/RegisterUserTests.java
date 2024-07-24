import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.example.UserInfo.UserInfo;
import org.example.UserInfo.UserRandomizer;
import org.example.UserInfo.UserSteps;
import org.example.pageObject.Constants;
import org.example.pageObject.RegistrationPage;
import org.example.pageObject.SignInPage;
import org.example.webDriverSettings.WebDriverSettings;
import org.junit.*;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegisterUserTests {
    public WebDriver driver;
    public RegistrationPage registrationPage;
    public static String accessToken;
    public SignInPage signInPage;

    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.BURGER_MAIN_PAGE;
        driver = WebDriverSettings.setBrowser();
        registrationPage = new RegistrationPage(driver);
        driver.get(Constants.BURGER_MAIN_PAGE + Constants.REGISTER_PAGE);
        registrationPage.waitingForSignUpPageLoading();
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @AfterClass
    public static void cleanData() {
        if (accessToken != null) {
            UserSteps.deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Проверка успешной регистрации пользователя")
    public void testSuccessRegister() {
        UserInfo userInfo = UserRandomizer.userWithRandomData();
        signInPage = new SignInPage(driver);
        registrationPage.insertUserDataAndClick(userInfo);
        signInPage.waitingLoginPage();
        assertEquals("Перешли на страницу логина", Constants.BURGER_MAIN_PAGE + Constants.LOGIN_PAGE, driver.getCurrentUrl());
        accessToken = UserSteps.loginUser(userInfo).path("accessToken");
    }

    @Test
    @DisplayName("Проверка вывода напдиси о некорректном пароле")
    public void testErrorAfterWrongData() {
        UserInfo userInfo = UserRandomizer.userWithRandomData();
        userInfo.setPassword("12345");
        registrationPage.insertUserDataAndClick(userInfo);
        assertTrue("Ошибка о некорректном пароле отображается", registrationPage.checkWrongPasswordMessage());
        assertEquals("Пользователь остался на странице с регистрацией", Constants.BURGER_MAIN_PAGE + Constants.REGISTER_PAGE, driver.getCurrentUrl());
    }
}

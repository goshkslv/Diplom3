import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.example.UserInfo.UserInfo;
import org.example.UserInfo.UserRandomizer;
import org.example.UserInfo.UserSteps;
import org.example.pageObject.Constants;
import org.example.pageObject.HomePage;
import org.example.pageObject.SignInPage;
import org.example.pageObject.UserProfilePage;
import org.example.webDriverSettings.WebDriverSettings;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class UserProfileTests {
    private static String accessToken;
    public WebDriver driver;
    public static UserInfo userInfo;
    public HomePage homePage;
    public UserProfilePage userProfilePage;


    @Before
    public void startUp(){
        driver = WebDriverSettings.setBrowser();
        homePage = new HomePage(driver);
        LoginUserTest.signInPage = new SignInPage(driver);
        userProfilePage = new UserProfilePage(driver);
        RestAssured.baseURI = Constants.BURGER_MAIN_PAGE;
        userInfo = UserRandomizer.userWithRandomData();
        accessToken = UserSteps.createNewUser(userInfo).path("accessToken");
        driver.get(Constants.BURGER_MAIN_PAGE);
        homePage.waitingForMainPageLoading();
        homePage.pushMainSighInButton();
        LoginUserTest.signInPage.insertCredintalsAndButtonClick(userInfo);
    }

    @After
    public void tearDown(){
        // Закрой браузер
        driver.quit();
        UserSteps.deleteUser(accessToken);
        }


    @Test
    @DisplayName("Выход из профиля кнопкой Выход")
    public void testExitProfile(){
        homePage.pushMyAccountButton();
        userProfilePage.waitingUserProfileLoad();
        userProfilePage.pushProfileExitButton();
        LoginUserTest.signInPage.waitingLoginPage();
        assertEquals("Пользователь перешел на страницу логина", Constants.BURGER_MAIN_PAGE+Constants.LOGIN_PAGE,driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход на главную после нажатия на лого")
    public void testMoveToMainPageAfterPuckLogo(){
        homePage.pushMyAccountButton();
        userProfilePage.waitingUserProfileLoad();
        userProfilePage.pushLogoButton();
        homePage.waitingForMainPageLoading();
        assertEquals("Пользователь перешел на главную страницу", Constants.BURGER_MAIN_PAGE+"/",driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход в профиль пользователя по кнопке Личный кабинет")
    public void testMoveToUserProfile(){
        homePage.pushMyAccountButton();
        userProfilePage.waitingUserProfileLoad();
        assertEquals("Пользователь перешел на страницу личного кабинета", Constants.BURGER_MAIN_PAGE+Constants.USER_PROFILE,driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход к конструктору по кнопке Конструктор")
    public void testMoveToConstructorAfterPushConstructorButton(){
        homePage.pushMyAccountButton();
        userProfilePage.waitingUserProfileLoad();
        userProfilePage.pushConstructorButton();
        homePage.waitingForMainPageLoading();
        assertEquals("Пользователь перешел на главную страницу", Constants.BURGER_MAIN_PAGE+"/",driver.getCurrentUrl());
    }






}

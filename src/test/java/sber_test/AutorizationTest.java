package sber_test;

import PageObject.AutorizationPage;
import PageObject.RegistrationPage;
import org.testng.annotations.Test;

public class AutorizationTest extends TestBase{
    @Test
    public static void autorizationTest(){
        AutorizationPage.openPage();
        // Подождем пока прогрузиться страница регистрации
        AutorizationPage.loaderPage();
        // Заполним логин и пароль
        RegistrationPage.inputTextField("Email", getConfigParametr("autorizationEmail"));
        RegistrationPage.inputTextField("Пароль", getConfigParametr("password"));
        // Попробуем авторизоваться
        AutorizationPage.clickAutorizationButton();
        // Проверим авторизацию
        AutorizationPage.assertAutorization();
    }

    @Test
    public static void failAutorizationTest(){
        AutorizationPage.openPage();
        // Подождем пока прогрузиться страница регистрации
        AutorizationPage.loaderPage();
        // Заполним логин и пароль
        RegistrationPage.inputTextField("Email", getConfigParametr("autorizationEmail"));
        RegistrationPage.inputTextField("Пароль", getConfigParametr("failPassword"));
        // Попробуем авторизоваться
        AutorizationPage.clickAutorizationButton();
        // Проверим авторизацию
        AutorizationPage.assertFailAutorization();
    }
}

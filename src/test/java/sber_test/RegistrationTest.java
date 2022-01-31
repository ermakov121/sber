package sber_test;

import PageObject.RegistrationPage;
import org.testng.annotations.Test;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RegistrationTest extends TestBase{

    @Test
    public static void simpleRegistrationTest(){
        RegistrationPage.openPage();
        // Подождем пока прогрузиться страница регистрации
        RegistrationPage.loaderPage();
        // Заполним поля
        RegistrationPage.inputTextField("Имя", getConfigParametr("loginName"));
        String currentTime = new SimpleDateFormat("MMdd_HHmmss").format(Calendar.getInstance().getTime());
        RegistrationPage.inputTextField("Email", getConfigParametr("email") + currentTime + "@mail.ru");
        RegistrationPage.inputPassword(getConfigParametr("password"));
        // Нажимаем кнопку "Зарегистрироваться"
        RegistrationPage.clickRegButton();
        // Проверим уведомление о том, что такой пользователь уже зарегистрирован
        RegistrationPage.assertRegistration();
    }

    @Test
    public static void failedRegistrationTest(){
        RegistrationPage.openPage();
        // Подождем пока прогрузиться страница регистрации
        RegistrationPage.loaderPage();
        // Заполним поля
        RegistrationPage.inputTextField("Имя", getConfigParametr("loginName"));
        String currentTime = new SimpleDateFormat("MMdd_HHmmss").format(Calendar.getInstance().getTime());
        RegistrationPage.inputTextField("Email", getConfigParametr("failedEmail"));
        RegistrationPage.inputPassword(getConfigParametr("password"));
        // Нажимаем кнопку "Зарегистрироваться"
        RegistrationPage.clickRegButton();
        // Проверим, что регистрация прошла успешно
        RegistrationPage.assertFailedRegistration();
    }
}
package PageObject;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import sber_test.TestBase;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Condition.*;

public class RegistrationPage {

    public static final SelenideElement regButton = $(byXpath("//button[. = 'Зарегистрироваться']"));

    // Дождемся пока страница регистрации прогрузиться
    public static void loaderPage(){
        regButton.shouldBe(visible);
    }

    // Метод для заполнения текстового поля на странице регистрации
    public static void inputTextField(String nameField, String value){
        try {
            $(byXpath("//label[. = '" + nameField + "']//following-sibling::input")).setValue(value);
        }
        catch (Exception e){
            System.out.println("Ошибка при заполнении поля " + nameField);
            e.printStackTrace();
        }
    }

    // Метод для заполнения и подтверждения пароля
    public static void inputPassword(String password){
        try{
            $(byXpath("//input[@placeholder = 'Введите пароль']")).setValue(password);
            $(byXpath("//input[@placeholder = 'Повторите пароль']")).setValue(password);
        }
        catch (Exception e){
            System.out.println("Ошибка при заполнении паролей");
        }
    }

    // Метод нажатия кнопки "Зарегистрироваться"
    public static void clickRegButton(){
        try{
            regButton.shouldBe(visible, Duration.ofSeconds(10)).click();
        }
        catch (Exception e){
            System.out.println("Ошибка при нажатии на кнопку регистрации");
            e.printStackTrace();
        }
    }

    // Метод, который проверяет успешную регистрацию
    public static void assertRegistration(){
        try {
            $(byText("Теперь вы можете войти используя свой email и пароль!")).shouldBe(visible);
            System.out.println("Присутствует сообщение об успешной регистрации");
        }
        catch (NoSuchElementException e){
            System.out.println("Сообщения об успешной регистрации нет на странице");
            e.printStackTrace();
        }
    }

    // Метод, который проверяет, что есть уведомление о том, что такой пользователь уже существует
    public static void assertFailedRegistration(){
        try {
            $(byText("Пользователь с таким email уже зарегистрирован!")).shouldBe(visible);
            System.out.println("Присутствует сообщение, что такой пользователь уже существует");
        }
        catch (NoSuchElementException e){
            System.out.println("Сообщения, что такой пользователь уже существует нет на странице");
            e.printStackTrace();
        }
    }

    // Открываем страницу регистрации
    public static void openPage(){
        Selenide.open(TestBase.getConfigParametr("loginPage"));
    }
}

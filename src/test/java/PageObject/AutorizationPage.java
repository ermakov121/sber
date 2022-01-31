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

public class AutorizationPage {

    private static final SelenideElement autorizationButton = $(byXpath("//button[. = 'Войти']"));
    private static final SelenideElement failAutorizationButton = $(byText("Email пароль неверный!"));

    // Дождемся пока страница авторизации прогрузиться
    public static void loaderPage(){
        autorizationButton.shouldBe(visible);
    }

    // Метод для нажатия кнопки "Войти"
    public static void clickAutorizationButton(){
        autorizationButton.click();
    }

    // Проверка авторизации
    public static void assertAutorization(){
        try {
            $(byXpath("//a[contains(., 'Kirill')]")).shouldBe(visible);
            System.out.println("Авторизация успешна!");
        }
        catch (NoSuchElementException e){
            System.out.println("Произошла ошибка при авторизации");
            e.printStackTrace();
        }
    }

    // Открываем страницу авторизации
    public static void openPage(){
        Selenide.open(TestBase.getConfigParametr("autorizationPage"));
    }

    // Метод для проверки ошибки авторизации
    public static void assertFailAutorization(){
        try {
            failAutorizationButton.shouldBe(visible);
            System.out.println("Авторизация не прошла, все ОК");
        }
        catch (NoSuchElementException e){
            System.out.println("На странице отсутствует ошибка об авторизации");
            e.printStackTrace();
        }

    }


}

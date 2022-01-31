package sber_test;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class TestBase {

    @BeforeMethod(description = "Запускаем Chrome")
    public void setup(){
        Selenide.open(TestBase.getConfigParametr("url"));
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(){
        Selenide.closeWebDriver();
    }

    public static String getConfigParametr(String param) {
        try {
            Properties properties = new Properties();
            properties.load(new InputStreamReader(new FileInputStream("src/test/resources/conf.properties"), "windows-1251"));
            String property = properties.getProperty(param);
            if (property == null || property.trim().isEmpty()) {
                System.out.println("ОШИБКА: в конфигурационном файле не указан параметр " + param);
            }
            return property.trim();
        } catch (IOException e) {
            System.out.println("ОШИБКА: Конфигурационный файл отсутствует!");
        }
        return "";
    }
}

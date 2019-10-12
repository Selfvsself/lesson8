package ru.homework;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class BaseTest {
    public static WebDriver driver;
    public static String baseUrl;
    public static Properties properties = TestProperties.getInstance().getProperties();

    @BeforeClass
    public static void setUp() {
        driver = Init.getDriver();
        baseUrl = String.valueOf(properties.get("baseUrl"));
    }

    @AfterClass
    public static void close() {
        driver.close();
    }
}

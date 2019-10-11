package ru.homework;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class BaseTest {
    protected static WebDriver driver;
    public static String baseUrl;
    public static Properties properties = TestProperties.getInstance().getProperties();

    @Before
    public static void setUp() {
        driver = Init.getDriver();
        baseUrl = String.valueOf(properties.get("baseUrl"));
    }

    @After
    public static void close() {
        driver.close();
    }

}

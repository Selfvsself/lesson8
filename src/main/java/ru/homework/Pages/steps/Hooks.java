package ru.homework.Pages.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import ru.homework.Init;

public class Hooks {
    private WebDriver driver;
    @Before
    public void prepareData() {
        System.out.println("before");
        String browser = System.getProperty("browser", "chrome");
        Init.setBrowser(browser);
        driver = Init.getDriver();
    }

    @After
    public void clearData() {
        driver.quit();
    }
}

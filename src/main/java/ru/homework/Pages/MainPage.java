package ru.homework.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.homework.TestProperties;

import java.util.Properties;

public class MainPage extends BasePage {
    public static Properties properties = TestProperties.getInstance().getProperties();

    @FindBy(xpath = "//span[@class='kit-content-constraint_lg_3 kit-text_s']")
    public WebElement anchor;

    private String mainMenuXpath = "//button[@aria-label='Меню %s']";

    private String subMainMenuXpath = "//a[@class='lg-menu__sub-link' and text()='Ипотека на готовое жильё']";

    public MainPage() {
        super();
        driver.get(String.valueOf(properties.get("baseUrl")));
        waitForElement(anchor);
    }

    @Step("Кликаем главное меню по пункту {nameMenu}")
    public WebElement clickToMainMenu(String nameMenu) {
        WebElement paragraphMainMenu = driver.findElement(By.xpath(String.format(mainMenuXpath, nameMenu)));
        waitAndClickElement(paragraphMainMenu);
        return paragraphMainMenu;
    }

    @Step("Кликаем в подменю по пункту {nameSubMenu}")
    public WebElement clickToSubMainMenu(String nameSubMenu) {
        WebElement paragraphMainMenu = driver.findElement(By.xpath(String.format(subMainMenuXpath, nameSubMenu)));
        waitAndClickElement(paragraphMainMenu);
        return paragraphMainMenu;
    }
}

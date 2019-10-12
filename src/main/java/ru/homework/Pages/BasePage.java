package ru.homework.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.homework.Init;

public abstract class BasePage {
    WebDriver driver;
    WebDriverWait wait;

    public BasePage() {
        driver = Init.getDriver();
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5, 500);
    }

    public WebElement waitForElement(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitAndClickElement(WebElement element) {
        waitForElement(element).click();
    }
}

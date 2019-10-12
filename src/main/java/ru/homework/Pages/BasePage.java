package ru.homework.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.homework.Init;

import java.util.function.Function;

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

    public void waitToChangeValueOnElement(WebElement element, String oldValue) {
        wait.until(new Function<WebDriver, Object>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return !element.getAttribute("value").equals(oldValue);
            }
        });
    }

    public void waitToEqualValueOnElement(WebElement element, String newValue) {
        wait.until(new Function<WebDriver, Object>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return element.getAttribute("value").contains(newValue);
            }
        });
    }
}

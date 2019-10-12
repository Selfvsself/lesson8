package ru.homework.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class MortgagePage extends BasePage {

    @FindBy(xpath = "//span[@class='kit-content-constraint_lg_3 kit-text_s']")
    private WebElement anchor;

    @FindBy(xpath = "//input[@id='estateCost']")
    private WebElement estateCost;

    @FindBy(xpath = "//input[@id='initialFee']")
    private WebElement initialFee;

    @FindBy(xpath = "//input[@id='creditTerm']")
    private WebElement creditTerm;

    @FindBy(xpath = "//div[text()='Есть зарплатная карта Сбербанка']/following-sibling::div//label")
    private WebElement switcherHaveCardSberbank;

    @FindBy(xpath = "//div[text()='Есть возможность подтвердить доход справкой']")
    private WebElement switcherConfimPaper;

    @FindBy(xpath = "//div[text()='Молодая семья']/following-sibling::div//label")
    private WebElement switcherYoungFamily;

    @FindBy(xpath = "//span[@data-test-id='amountOfCredit']")
    private WebElement amountOfCredit;

    @FindBy(xpath = "//span[@data-test-id='monthlyPayment']")
    private WebElement monthlyPayment;

    @FindBy(xpath = "//span[@data-test-id='requiredIncome']")
    private WebElement requiredIncome;

    @FindBy(xpath = "//span[@data-test-id='rate']")
    private WebElement rate;

    @FindBy(xpath = "//h2[contains(text(),'Рассчитайте ипотеку')]")
    private WebElement header;

    private String oldTextValue;

    public MortgagePage() {
        super();
        waitForElement(anchor);
    }

    @Step("Вводим стоимость недвижимости {value}")
    public void inputEstateCost(String value) {
        driver.switchTo().frame(0);
        waitForElement(estateCost);
        estateCost.clear();
        estateCost.sendKeys(value);
        driver.switchTo().defaultContent();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
    }

    @Step("Вводим первоначальный взнос {value}")
    public void inputInitialFee(String value) {
        driver.switchTo().frame(0);
        waitForElement(initialFee);
        oldTextValue = initialFee.getAttribute("value");
        waitToChangeValueOnElement(initialFee, oldTextValue);
        initialFee.clear();
        initialFee.sendKeys(value);
        try {
            waitToEqualValueOnElement(initialFee, formantString(value));
        } catch (Throwable e) {
            initialFee.clear();
            initialFee.sendKeys(value);
        }
        driver.switchTo().defaultContent();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
    }

    private String formantString(String str) {
        char[] chars = str.toCharArray();
        ArrayList<Character> arrayList = new ArrayList<>();
        int index = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (index > 2) {
                arrayList.add(' ');
                index = 0;
            }
            arrayList.add(chars[i]);
            index++;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = arrayList.size() - 1; i >= 0; i--) {
            stringBuilder.append(arrayList.get(i));
        }
        return stringBuilder.toString();
    }

    @Step("Вводим срок кредита {value}")
    public void inputCreditTerm(String value) {
        driver.switchTo().frame(0);
        waitForElement(creditTerm);
        creditTerm.clear();
        creditTerm.sendKeys(value);
        try {
            waitToEqualValueOnElement(creditTerm, value);
        } catch (Throwable e) {
            creditTerm.clear();
            creditTerm.sendKeys(value);
        }
        driver.switchTo().defaultContent();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
    }

    @Step("Кликаем на radiobutton Есть зарплатная карта Сбербанка")
    public void switchHaveCardSberbank() {
        driver.switchTo().frame(0);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();"
                , creditTerm);
        switcherHaveCardSberbank.click();
        driver.switchTo().defaultContent();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
    }

    @Step("Ожидаем появление пункта Есть возможность подтвердить справкой")
    public void waitConfirmPaper() {
        driver.switchTo().frame(0);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();"
                , creditTerm);
        try {
            waitForElement(switcherConfimPaper);
        } catch (Throwable e) {
            switchHaveCardSberbank();
        }
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
        driver.switchTo().defaultContent();
    }

    @Step("Кликаем на radiobutton Молодая семья")
    public void switchYoungFamily() {
        driver.switchTo().frame(0);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();"
                , creditTerm);
        while (!switcherYoungFamily.getAttribute("class").contains("checked")) {
            switcherYoungFamily.click();
            driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
        }
        driver.switchTo().defaultContent();
    }

    @Step("Получаем значение сумма кредита")
    public String getAmountOfCredit() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();"
                , header);
        driver.switchTo().frame(0);
        waitToStopChangeElement(amountOfCredit);
        String answer = amountOfCredit.getText();
        driver.switchTo().defaultContent();
        return answer;
    }

    @Step("Получаем значение ежемесячный платеж")
    public String getMonthlyPayment() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();"
                , header);
        driver.switchTo().frame(0);
        waitToStopChangeElement(monthlyPayment);
        String answer = monthlyPayment.getText();
        driver.switchTo().defaultContent();
        return answer;
    }

    @Step("Получаем значение необходимый доход")
    public String getRequiredIncome() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();"
                , header);
        driver.switchTo().frame(0);
        waitToStopChangeElement(requiredIncome);
        String answer = requiredIncome.getText();
        driver.switchTo().defaultContent();
        return answer;
    }

    @Step("Получаем значение процентная ставка")
    public String getRate() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();"
                , header);
        driver.switchTo().frame(0);
        waitToStopChangeElement(rate);
        String str = rate.getText();
        driver.switchTo().defaultContent();
        return str;
    }


    private void waitToStopChangeElement(WebElement element) {
        wait.until(new Function<WebDriver, Object>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                String value = element.getText();
                driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
                return element.getText().equals(value);
            }
        });
    }
}

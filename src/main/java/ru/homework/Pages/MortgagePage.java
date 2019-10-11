package ru.homework.Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    private String oldTextValue;

    public MortgagePage() {
        super();
        waitForElement(anchor);
    }

    public void inputEstateCost(int value) {
        driver.switchTo().frame(0);
        waitForElement(estateCost);
        estateCost.clear();
        estateCost.sendKeys(Integer.toString(value));
        driver.switchTo().defaultContent();
    }

    public void inputInitialFee(int value) {
        driver.switchTo().frame(0);
        waitForElement(initialFee);
        oldTextValue = initialFee.getAttribute("value");
        waitToChangeValueOnElement(initialFee, oldTextValue);
        initialFee.clear();
        initialFee.sendKeys(Integer.toString(value));
        driver.switchTo().defaultContent();
    }

    public void inputCreditTerm(int value) {
        driver.switchTo().frame(0);
        waitForElement(creditTerm);
        creditTerm.clear();
        creditTerm.sendKeys(Integer.toString(value));
        waitToEqualValueOnElement(creditTerm, Integer.toString(value));
        driver.switchTo().defaultContent();
    }

    public void switchHaveCardSberbank() {
        driver.switchTo().frame(0);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();"
                , creditTerm);
        switcherHaveCardSberbank.click();
        driver.switchTo().defaultContent();
    }

    public void waitConfirmPaper() {
        driver.switchTo().frame(0);
        waitForElement(switcherConfimPaper);
        driver.switchTo().defaultContent();
    }

    public void switchYoungFamily() {
        driver.switchTo().frame(0);
        switcherYoungFamily.click();
        driver.switchTo().defaultContent();
    }

    private int textToInt(String text) {
        StringBuilder answer = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if ( Character.isLetterOrDigit(ch) ) {
                answer.append(ch);
            }
        }
        return Integer.parseInt(answer.toString());
    }

    public int getAmountOfCredit() {
        driver.switchTo().frame(0);
        waitToStopChangeElement(amountOfCredit);
        int answer = textToInt(amountOfCredit.getText());
        driver.switchTo().defaultContent();
        return answer;
    }

    public int getMonthlyPayment() {
        driver.switchTo().frame(0);
        waitToStopChangeElement(monthlyPayment);
        int answer = textToInt(monthlyPayment.getText());
        driver.switchTo().defaultContent();
        return answer;
    }

    public int getRequiredIncome() {
        driver.switchTo().frame(0);
        waitToStopChangeElement(requiredIncome);
        int answer = textToInt(requiredIncome.getText());
        driver.switchTo().defaultContent();
        return answer;
    }

    public double getRate() {
        driver.switchTo().frame(0);
        waitToStopChangeElement(rate);
        String str = rate.getText();
        str = str.replaceAll(",", ".");
        double answer = Double.parseDouble(str.substring(0, str.length() - 2));
        driver.switchTo().defaultContent();
        return answer;
    }


    public void waitToStopChangeElement(WebElement element) {
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

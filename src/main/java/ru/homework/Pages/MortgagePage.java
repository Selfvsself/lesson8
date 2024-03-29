package ru.homework.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
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

    private String xpathToRadioButton = "//div[text()='%s']/following-sibling::div//label";

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
        String oldValue = monthlyPayment.getText();
        while (!estateCost.getAttribute("value").equals("")) {              //Удаляем старое значение из поля
            estateCost.sendKeys(Keys.BACK_SPACE);
        }
        estateCost.sendKeys(value);                                             //Вводим новое значение
        try {
            waitToEqualValueOnElement(estateCost, formantString(value));        //Ждем пока значение в поле будет равно вводимому
        } catch (org.openqa.selenium.TimeoutException e) {                      //Если не проходит ожидалка то вводим значение еще раз
            while (!estateCost.getAttribute("value").equals("")) {
                estateCost.sendKeys(Keys.BACK_SPACE);
            }
            estateCost.sendKeys(value);
        }
        waitChangeValue(monthlyPayment, oldValue);                              //Ждем пока поменяется значение о ежемесячном платеже
        driver.switchTo().defaultContent();
    }

    @Step("Вводим первоначальный взнос {value}")
    public void inputInitialFee(String value) {
        driver.switchTo().frame(0);
        waitForElement(initialFee);
        String oldValue = monthlyPayment.getText();
        while (!initialFee.getAttribute("value").equals("")) {
            initialFee.sendKeys(Keys.BACK_SPACE);
        }
        initialFee.sendKeys(value);
        try {
            waitToEqualValueOnElement(initialFee, formantString(value));
            waitChangeValue(monthlyPayment, oldValue);
        } catch (org.openqa.selenium.TimeoutException e) {
            while (!initialFee.getAttribute("value").equals("")) {
                initialFee.sendKeys(Keys.BACK_SPACE);
            }
            initialFee.sendKeys(value);
        }
        driver.switchTo().defaultContent();
    }

    private String formantString(String str) {                                          //Метод для сравнения значений введеных и которые нужно ввести
        char[] chars = str.toCharArray();                                               //Метод преобразует строку 5308000 в 5 308 000
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
        String oldValue = monthlyPayment.getText();
        while (!creditTerm.getAttribute("value").equals("")) {
            creditTerm.sendKeys(Keys.BACK_SPACE);
        }
        creditTerm.sendKeys(value);
        try {
            waitToEqualValueOnElement(creditTerm, value);
            waitChangeValue(monthlyPayment, oldValue);
        } catch (org.openqa.selenium.TimeoutException e) {
            while (!creditTerm.getAttribute("value").equals("")) {
                creditTerm.sendKeys(Keys.BACK_SPACE);
            }
            creditTerm.sendKeys(value);
        }
        driver.switchTo().defaultContent();
    }

    @Step("Кликаем на radiobutton {name}")
    public void switchRadionButton(String name) {
        driver.switchTo().frame(0);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", creditTerm);
        WebElement element = driver.findElement(By.xpath(String.format(xpathToRadioButton, name)));
        boolean flagSwitch = element.getAttribute("class").contains("checked");
        int attempt = 3;                                                                            //Количество максимальных попыток изменить radiobutton
        String oldValue = monthlyPayment.getText();
        while (flagSwitch == element.getAttribute("class").contains("checked")) {                  //До тех пока не поменятеся переключатель будем 3 раза пробовать его переключить
            if (attempt < 0) {
                break;
            }
            attempt--;
            element.click();
        }
        waitChangeValue(monthlyPayment, oldValue);
        driver.switchTo().defaultContent();
    }

    @Step("Ожидаем появление пункта {name} c radiobutton")
    public void waitRadioButton(String name) {
        driver.switchTo().frame(0);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", creditTerm);
        WebElement element = driver.findElement(By.xpath(String.format(xpathToRadioButton, name)));
        waitForElement(element);
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

    private void waitChangeValue(WebElement element, String value) {
        wait.until(new Function<WebDriver, Object>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                waitToStopChangeElement(element);
                return !element.getText().equals(value);
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

package ru.homework.Pages.steps;

import io.cucumber.java.ru.Когда;
import org.junit.Assert;
import ru.homework.Pages.MortgagePage;

public class CucumberMortgagePage {
    MortgagePage mortgagePage = new MortgagePage();

    @Когда("вводим в поле стоимость недвижимости значение \"(.+)\"")
    public void inputEstateCost(String value) {
        mortgagePage.inputEstateCost(value);
    }

    @Когда("вводим в поле первоначальный взнос значение \"(.+)\"")
    public void inputInitialFee(String value) {
        mortgagePage.inputInitialFee(value);
    }

    @Когда("вводим в поле срок кредита значение \"(.+)\"")
    public void inputCreditTerm(String value) {
        mortgagePage.inputCreditTerm(value);
    }

    @Когда("нажимаем на переключатель \"(.+)\"")
    public void switchHaveCardSberbank(String name) {
        mortgagePage.switchRadionButton(name);
    }

    @Когда("ждем появления пункта \"(.+)\"")
    public void waitConfirmPaper(String name) {
        mortgagePage.waitRadioButton(name);
    }

    @Когда("сравниваем поле сумма кредита со значением \"(.+)\"")
    public void getAmountOfCredit(String value) {
        Assert.assertEquals(mortgagePage.getAmountOfCredit(), value);
    }

    @Когда("сравниваем поле ежемесячный платеж со значением \"(.+)\"")
    public void getMonthlyPayment(String value) {
        Assert.assertEquals(mortgagePage.getMonthlyPayment(), value);
    }

    @Когда("сравниваем поле необходимый доход со значением \"(.+)\"")
    public void getRequiredIncome(String value) {
        Assert.assertEquals(mortgagePage.getRequiredIncome(), value);
    }

    @Когда("сравниваем поле процентная ставка со значением \"(.+)\"")
    public void getRate(String value) {
        Assert.assertEquals(mortgagePage.getRate(), value);
    }
}

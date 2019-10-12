package ru.homework;

/*Требования:
запуск через maven
allure отчет
скриншот при ошибке
cucumber bdd
page object
selenium
при любом изменени ползунков/значений/доп пунктов влитяющийх на процент - делать ожидалку изменения ежемесячного платежа

Сценарий:
1) перейти на https://www.sberbank.ru/person
2) В верхнем меню "навестись" на Ипотека - дождаться открытия выпдающего меню и выбрать "Ипотека на готовое жилье"
3) Заполнить поля
Стоимость недвижмости 5 180 000 ₽
Первоначальнй взнос 3 058 000 ₽
Срок кредита 30 лет
Снять галочкку - есть зарплатная карта сбербанка
дождаться появляения "есть возможность подтвержить доход справкой"
поставить галочку "молодая семья"

Проверить значение полей
Сумма кредита
2 122 000 ₽
Ежемесячный платеж
18 937 ₽
Необходимый доход
31 561 ₽
Процентная ставка
11% - тут ошибка (специально)
 */

import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import ru.homework.Pages.MainPage;
import ru.homework.Pages.MortgagePage;

public class SberbankTest extends BaseTest {

    @Test
    @Ignore
    @DisplayName("Тест для домашнего задания")
    public void testSberbank() {
        MainPage mainPage = new MainPage();
        mainPage.clickToMainMenu("Ипотека");
        mainPage.clickToSubMainMenu("Ипотека на готовое жильё");

        MortgagePage mortgagePage = new MortgagePage();
        mortgagePage.inputEstateCost("5180000");
        mortgagePage.inputInitialFee("3058000");
        mortgagePage.inputCreditTerm("30");
        mortgagePage.switchHaveCardSberbank();
        mortgagePage.waitConfirmPaper();
        mortgagePage.switchYoungFamily();

        Assert.assertEquals(mortgagePage.getAmountOfCredit(), "2 122 000 \u20BD");
        Assert.assertEquals(mortgagePage.getMonthlyPayment(), "17 998 \u20BD");
        Assert.assertEquals(mortgagePage.getRequiredIncome(), "29 997 \u20BD");
        Assert.assertEquals(mortgagePage.getRate(), "11%");
    }
}

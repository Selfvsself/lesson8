package ru.homework.Pages.steps;

import io.cucumber.java.ru.Когда;
import ru.homework.Pages.MainPage;

public class CucumberMainPage {
    MainPage mainPage = new MainPage();

    @Когда("кликаем в главном меню по пункту \"(.+)\"")
    public void clickToMainMenu(String nameMenu) {
        mainPage.clickToMainMenu(nameMenu);
    }

    @Когда("кликаем в по выподающему меню по пункту \"(.+)\"")
    public void clickToSubMainMenu(String nameSubMenu) {
        mainPage.clickToSubMainMenu(nameSubMenu);
    }
}

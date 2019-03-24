package com.timecamp.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;


@DefaultUrl("http://localhost:8080/")
public class HomePage extends PageObject {

    @FindBy(xpath = "//a[@href='http://localhost:8080/auth/login']")
    private WebElementFacade loginButton;


    public void openLoginPage(){
        loginButton.click();
    }
}

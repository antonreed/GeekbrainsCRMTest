package com.antonreed.CreateContact;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {
    private WebDriver driver;

    public ContactsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//a[@title = \"Создать контактное лицо\"]")
    private WebElement createContactButton;

    public ContactsPage clickCreateContactButton() {
        createContactButton.click();
        return this;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}

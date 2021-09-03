package com.antonreed;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavMenuElements {
    private static Actions actions;
    private WebDriver driver;

    public NavMenuElements(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        actions = new Actions(driver);
    }

    @FindBy(xpath = "//*[@id = \"main-menu\"]//span[contains(text(), \"Контрагенты\")]")
    private WebElement contragentsLink;

    @FindBy(xpath = "//*[@id = \"main-menu\"]//span[contains(text(), \"Контактные лица\")]")
    private WebElement contactsLink;

    @FindBy(xpath = "//a/span[contains(text(),'Проекты')]")
    private WebElement projects;

    @FindBy(xpath = "//a/span[contains(text(),'Мои проекты')]")
    private WebElement myProjects;

    public void clickContactsLink() {
        actions.moveToElement(contragentsLink).moveToElement(contactsLink).click().build().perform();
    }

    public void clickMyProjectsLink () {
        actions.moveToElement(projects).moveToElement(myProjects).click().build().perform();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}

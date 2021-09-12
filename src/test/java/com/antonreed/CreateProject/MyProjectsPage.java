package com.antonreed.CreateProject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyProjectsPage {
    private WebDriver driver;

    public MyProjectsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//a[@title = \"Создать проект\"]")
    private WebElement createProjectButton;

    public MyProjectsPage clickCreateProjectButton() {
        createProjectButton.click();
        return this;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}

package com.antonreed.CreateContact;

import com.antonreed.ConfProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

public class CreateContactPage {
    private WebDriver driver;

    public CreateContactPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[contains(@id, \"crm_contact_lastName\")]")
    private WebElement lastName;

    @FindBy(xpath = "//*[contains(@id, \"crm_contact_firstName\")]")
    private WebElement firstName;

    @FindBy(xpath = "//div[contains(@id, \"crm_contact_company\")]/a")
    private WebElement organizationSelect;

    @FindBy(xpath = "//*[@id = \"select2-drop\"]//input")
    private WebElement organization;

    @FindBy(xpath = "//*[contains(@id, \"crm_contact_jobTitle\")]")
    private WebElement position;

    @FindBy(xpath = "//button[contains(text(), \"Сохранить и закрыть\")]")
    private WebElement submitButton;


    public void inputLastName(String lastName) {
        this.lastName.sendKeys(lastName);
    }

    public void inputFirstName(String firstName) {
        this.firstName.sendKeys(firstName);
    }

    public void inputPosition(String position) {
        this.position.sendKeys(position);
    }

    public void selectOrganization(String organization) {
        organizationSelect.click();
        this.organization.sendKeys(organization);
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement selectedOrganization = driver.findElement(
                By.xpath("//*[@id = \"select2-drop\"]//span[contains(" +
                        "text(), \"" + ConfProperties.getProperty("organization") + "\")]"));

        wait.until(ExpectedConditions.visibilityOf(selectedOrganization));
        selectedOrganization.click();
    }

    public void clickSubmitButton() {
        submitButton.click();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}

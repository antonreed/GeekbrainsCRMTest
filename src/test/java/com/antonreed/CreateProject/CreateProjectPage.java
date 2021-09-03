package com.antonreed.CreateProject;

import com.antonreed.ConfProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateProjectPage {
    private static WebDriverWait wait;
    private final WebDriver driver;

    public CreateProjectPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 5, 100);
    }

    @FindBy(xpath = "//input[@name=\"crm_project[name]\"]")
    private WebElement projectName;

    @FindBy(xpath = "//div[contains(@id,'crm_project_company')]")
    private WebElement organizationSelect;

    @FindBy(xpath = "//div[@id = \"select2-drop\"]//input")
    private WebElement organization;

    @FindBy(xpath = "//button[contains(text(), \"Сохранить и закрыть\")]")
    private WebElement submitButton;

    public void inputProjectName(String organization) {
        projectName.sendKeys(organization);
    }

    public void selectOrganization(String organization) {
        organizationSelect.click();
        this.organization.sendKeys(organization);

        WebElement selectedOrganization = driver.findElement(
                By.xpath("//div[@id = \"select2-drop\"]//span[contains(" +
                        "text(), '" + ConfProperties.getProperty("organization") + "')]"));

        wait.until(ExpectedConditions.visibilityOf(selectedOrganization));
        selectedOrganization.click();
    }

    public void selectBusinessUnit(String businessUnit) {
        Select businessUnitSelect = new Select(driver.findElement(By
                .xpath("//select[@name=\"crm_project[businessUnit]\"]")));
        businessUnitSelect.selectByVisibleText(businessUnit);
    }

    public void selectCurator(String curator) {
        Select curatorSelect = new Select(driver.findElement(By
                .xpath("//select[@name=\"crm_project[curator]\"]")));
        curatorSelect.selectByVisibleText(curator);
    }

    public void selectRP(String rp) {
        Select rpSelect = new Select(driver.findElement(By
                .xpath("//select[@name=\"crm_project[rp]\"]")));
        rpSelect.selectByVisibleText(rp);
    }

    public void selectAdministratorUnit(String administrator) {
        Select administratorSelect = new Select(driver.findElement(By
                .xpath("//select[@name=\"crm_project[administrator]\"]")));
        administratorSelect.selectByVisibleText(administrator);
    }

    public void selectManagerUnit(String manager) {
        Select managerSelect = new Select(driver.findElement(By
                .xpath("//select[@name=\"crm_project[manager]\"]")));
        managerSelect.selectByVisibleText(manager);
    }

    public void selectContactMain(String contactMain) {
        Select contactMainSelect = new Select(driver.findElement(By
                .xpath("//select[@name=\"crm_project[contactMain]\"]")));
        contactMainSelect.selectByVisibleText(contactMain);
    }

    public void clickSubmitButton() {
        submitButton.click();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}

package com.antonreed;

import com.antonreed.CreateContact.ContactsPage;
import com.antonreed.CreateContact.CreateContactPage;
import com.antonreed.CreateProject.CreateProjectPage;
import com.antonreed.CreateProject.MyProjectsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Main {
    public static LoginPage loginPage;
    public static NavMenuElements navMenuElements;
    public static ContactsPage contactsPage;
    public static CreateContactPage createContactPage;
    public static MyProjectsPage myProjectsPage;
    public static CreateProjectPage createProjectPage;
    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeClass
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10, 100);

        navMenuElements = new NavMenuElements(driver);

        driver.manage().window().setSize(new Dimension(
                Integer.parseInt(ConfProperties.getProperty("windowWidth")),
                Integer.parseInt(ConfProperties.getProperty("windowsHeight"))));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("startPage"));

        new LoginPage(driver)
                .inputLogin(ConfProperties.getProperty("login"))
                .inputPassword(ConfProperties.getProperty("password"))
                .clickLoginButton();

        Assert.assertEquals(navMenuElements.getPageTitle(), driver.getTitle());
    }

    @Test
    public void test1_createContact() {
        contactsPage = new ContactsPage(driver);
        createContactPage = new CreateContactPage(driver);

        navMenuElements.clickContactsLink();

        wait.until(ExpectedConditions.titleIs(contactsPage.getPageTitle()));
        Assert.assertEquals(contactsPage.getPageTitle(), driver.getTitle());

        contactsPage.clickCreateContactButton();
        wait.until(ExpectedConditions.titleIs(createContactPage.getPageTitle()));
        Assert.assertEquals(createContactPage.getPageTitle(), driver.getTitle());

        createContactPage
                .inputLastName(ConfProperties.getProperty("newContactLastName"))
                .inputFirstName(ConfProperties.getProperty("newContactFirstName"))
                .selectOrganization(ConfProperties.getProperty("organization"))
                .inputPosition(ConfProperties.getProperty("contactPosition"))
                .clickSubmitButton();
        wait.until(ExpectedConditions.titleIs(ConfProperties.getProperty("pageTitleAfterCreatingContact")));
        Assert.assertEquals(contactsPage.getPageTitle(), driver.getTitle());

        Assert.assertEquals(ConfProperties.getProperty("contactPositiveMessage"), driver.findElement(By.
                cssSelector(".message")).getText());
    }

    @Test
    public void test2_createProject() {
        myProjectsPage = new MyProjectsPage(driver);
        createProjectPage = new CreateProjectPage(driver);

        navMenuElements.clickMyProjectsLink();
        wait.until(ExpectedConditions.titleIs(myProjectsPage.getPageTitle()));
        Assert.assertEquals(myProjectsPage.getPageTitle(), driver.getTitle());

        myProjectsPage.clickCreateProjectButton();
        wait.until(ExpectedConditions.titleIs(createProjectPage.getPageTitle()));
        Assert.assertEquals(createProjectPage.getPageTitle(), driver.getTitle());

        createProjectPage
                .inputProjectName(ConfProperties.getProperty("projectName"))
                .selectOrganization(ConfProperties.getProperty("organization"))
                .selectBusinessUnit(ConfProperties.getProperty("businessUnit"))
                .selectCurator(ConfProperties.getProperty("curator"))
                .selectRP(ConfProperties.getProperty("rp"))
                .selectAdministratorUnit(ConfProperties.getProperty("administrator"))
                .selectManagerUnit(ConfProperties.getProperty("manager"))
                .selectContactMain(ConfProperties.getProperty("contactMain"))
                .clickSubmitButton();
        wait.until(ExpectedConditions.titleIs(ConfProperties.getProperty("pageTitleAfterCreatingProject")));
        Assert.assertEquals(ConfProperties.getProperty("pageTitleAfterCreatingProject"), driver.getTitle());

        Assert.assertEquals(ConfProperties.getProperty("projectPositiveMessage"), driver.findElement(By.
                cssSelector(".message")).getText());
    }

    @AfterClass
    public static void endOfTests() {
        driver.quit();
    }
}

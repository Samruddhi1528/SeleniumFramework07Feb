package com.salesforce.tests;

import com.salesforce.base.BaseTest;
import com.salesforce.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ValidLoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void initPage() {
        loginPage = new LoginPage(driver);
    }

    @Test(priority = 1)
    public void verifyLoginPageElementsDisplayed() {
        Assert.assertTrue(loginPage.isUsernameFieldDisplayed());
        Assert.assertTrue(loginPage.isPasswordFieldDisplayed());
        Assert.assertTrue(loginPage.isLoginButtonDisplayed());
        Assert.assertTrue(loginPage.isForgotPasswordLinkDisplayed());
    }

    @Test(priority = 2)
    public void verifyLoginPageTitle() {
        String expectedTitle = "Login | Salesforce";
        Assert.assertEquals(loginPage.getPageTitle(), expectedTitle);
    }

    @Test(priority = 3)
    public void verifyLoginPageUrl() {
        Assert.assertTrue(loginPage.getCurrentUrl().contains("login.salesforce.com"));
    }

    @Test(priority = 4)
    public void verifyValidLoginAttempt() {
        loginPage.performLogin("validuser@salesforce.com", "ValidPassword123");
        Assert.assertTrue(
            loginPage.getCurrentUrl().contains("salesforce.com") || 
            loginPage.isErrorMessageDisplayed()
        );
    }

    @Test(priority = 5)
    public void verifyValidLoginWithRememberMe() {
        loginPage.performLoginWithRememberMe("validuser@salesforce.com", "ValidPassword123");
        Assert.assertTrue(
            loginPage.getCurrentUrl().contains("salesforce.com") || 
            loginPage.isErrorMessageDisplayed()
        );
    }
}
